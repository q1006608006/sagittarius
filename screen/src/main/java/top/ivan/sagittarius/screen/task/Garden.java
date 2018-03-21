package top.ivan.sagittarius.screen.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.ivan.sagittarius.screen.Rule;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.PartsManager;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.defalutparts.DefaultDownloader;
import top.ivan.sagittarius.screen.download.Downloader;
import top.ivan.sagittarius.screen.operator.persist.DefaultPersist;
import top.ivan.sagittarius.screen.operator.persist.Persist;
import top.ivan.sagittarius.screen.operator.spread.DefaultSpread;
import top.ivan.sagittarius.screen.operator.spread.Spread;
import top.ivan.sagittarius.screen.operator.spread.SpreadHandle;
import top.ivan.sagittarius.screen.operator.spread.SpreadWallet;
import top.ivan.sagittarius.screen.parse.Griddle;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Garden implements SpreadHandle {
    private static Logger logger = LogManager.getLogger(Garden.class);
    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10); //线程池

    {
        executor.setKeepAliveTime(1, TimeUnit.DAYS);
    }
    //组件管理器
    private PartsManager manager;
    //上下文环境映射表
    private Map<String, SeedContext> contextMap;
    //阻塞任务映射表
    private final Map<String, TimeLock> timeMap = new ConcurrentHashMap<>();
    //下载行为执行结束后的回调通知
    private Callback callback;
    //扩散管理器
    private Spread spread;
    //持久化管理器
    private Persist persist;
    //默认的下载器
    private Downloader downloader;
    //最大重试次数
    private int maxFailedCount = 5;
    //重试映射表
    private Map<Seed, Integer> errorMap = new ConcurrentHashMap<>();
    private boolean isStart = false;
    //是否异步执行
    private boolean isAsync = true;

    public Garden(PartsManager manager) {
        this.manager = manager;
        contextMap = new LinkedHashMap<>();
        spread = new DefaultSpread(manager, this);
        persist = new DefaultPersist(manager);
        callback = this::develop;
        downloader = new DefaultDownloader();
    }

    /**
     * 持有上下文环境
     * @param context
     */
    public void hold(SeedContext context) {
        contextMap.put(context.getLocation(), context);
        if (isStart) {
            startSeedContext(context);
        }
    }

    /**
     * 开始执行
     */
    public void start() {
        if (isStart) {
            throw new RuntimeException("double running!");
        }
        Thread debugThread = new Thread(()-> {

        });
//        startHoldThread();
        Collection<SeedContext> contexts = contextMap.values();
        for (SeedContext context : contexts) {
            startSeedContext(context);
        }
    }

    /**
     * 任务执行入口
     * @param seed
     */
    @Override
    public void accept(Seed seed) {
        if (null == seed) {
            return;
        }
        SpreadWallet wallet = seed.getSpread();
        Downloader downloader = wallet.getDownloader() == null ? this.downloader : manager.getDownLoader(wallet.getDownloader());
        if (isAsync) {
            executor.execute(() -> { //异步执行
                try {
                    download(seed, downloader, callback,true);
                } catch (IOException e) {
                    onFailed(seed, e);
                }
            });
        } else {
            try {
                download(seed, downloader, null,false);
                develop(seed);
            } catch (IOException e) {
                onFailed(seed, e);
            }
        }
    }

    /**
     * 下载之后的解析及持久化和扩散操作
     * @param seed
     */
    public void develop(Seed seed) {
        if (null == seed) {
            return;
        }
        try {
            String ruleId = seed.getSpread().getRuleId();
            SeedContext context = contextMap.get(seed.getLocation());
            Rule rule = context.getRuleMap().get(ruleId);
            seed.getSpread().setRule(rule);
            Griddle griddle = rule.getGriddle();
            if (null != griddle) {
                Map<String, String> result = griddle.doFilter(seed); //解析文本
                if (null != seed.getStorage()) {
                    seed.getStorage().putAll(result);
                } else {
                    seed.setStorage(result);
                }
            }
            persist.process(seed, seed.getSpread().getRule().getPersistList()); //持久化
            spread.spread(seed, seed.getSpread().getRule().getSpreadList(), contextMap.get(seed.getLocation())); //扩散
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        spread.stop();
        executor.shutdown();
        isStart = false;
    }

    public boolean isAsync() {
        return isAsync;
    }

    public void setAsync(boolean async) {
        isAsync = async;
    }

    /**
     * 监听定时任务
     * @param context
     */
    private void startSeedContext(SeedContext context) {
        List<Seed> seeds = context.getBaseSeeds();
        if (seeds != null) {
            for (Seed seed : seeds) {
                spread.spread(seed, Collections.singletonList(seed.getSpread()), context);
            }
        }
        List<SpreadWallet> activeSpreads = context.getActiveSpreads();
        if (null != activeSpreads) {
            for (SpreadWallet wallet : activeSpreads) {
                spread.listen(wallet, context);
            }
        }
    }

    /**
     * 下载任务
     * @param seed
     * @param downloader
     * @param callback
     * @param isAsync
     * @throws IOException
     */
    private void download(Seed seed, Downloader downloader, Callback callback,boolean isAsync) throws IOException {
        if (!seed.isDownloadable()) {
            if (isAsync) {
                callback.callback(seed);
            } else {
                return;
            }
        }
        long interval = seed.getSite().getInterval();  //向服务器发起请求间隔
        if (interval > 0) {
            TimeLock timeLock;
            Long cur = System.currentTimeMillis();
            synchronized (timeMap) {
                timeLock = timeMap.get(seed.getLocation());
                if (timeLock == null) {
                    timeLock = new TimeLock(cur);
                    timeMap.put(seed.getLocation(), timeLock);
                }
            }
            synchronized (timeLock) {
                cur = System.currentTimeMillis();
                if (cur < timeLock.getTimeMillions()) { //未达到可再次请求时间，等待
                    try {
                        Thread.sleep(timeLock.getTimeMillions() - cur);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                timeLock.addLockMillions(interval);
            }
            downloader.load(seed, callback); //开始下载
        } else {
            downloader.load(seed, callback);
        }
    }

    /**
     * 失败重试
     * @param seed
     * @param throwable
     */
    private void onFailed(Seed seed, Throwable throwable) {
        int failedTime = errorMap.getOrDefault(seed, 0);
        if (failedTime > maxFailedCount) {
            logger.error(String.format("load %s:%s failed,retry gather than %d times,give up this seed", seed.toString(), seed.getUrl(), maxFailedCount));
        } else {
            errorMap.put(seed, ++failedTime);
            seed.setDownloadable(true);
            logger.error(String.format("load %s failed,caused by: [%s],start %d times retry...", seed.toString(), throwable.getMessage(), failedTime));
//            spread.spread(seed, Collections.singletonList(seed.getSpread()), contextMap.get(seed.getLocation()));
            this.accept(seed);
        }
    }

    private class ShutdownSeed extends Seed {
    }

    private static class TimeLock {
        private Long timeMillions;
        public Object lock;

        public TimeLock(Long timeMillions) {
            lock = new Object();
            this.timeMillions = timeMillions;
        }

        public synchronized void addLockMillions(long ms) {
            timeMillions = System.currentTimeMillis() + ms;
        }

        public synchronized long getTimeMillions() {
            return timeMillions;
        }

        @Override
        public String toString() {
            return "TimeLock{" +
                    "timeMillions=" + timeMillions +
                    ", lock=" + lock +
                    '}';
        }
    }
}
