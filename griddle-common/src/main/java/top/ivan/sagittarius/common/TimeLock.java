package top.ivan.sagittarius.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TimeLock implements Comparable <TimeLock> {
    private static Logger logger = LogManager.getLogger(TimeLock.class);

    private static final PriorityBlockingQueue<TimeLock> queue = new PriorityBlockingQueue<>();  //优先级队列，用于保存任务
    private static ExecutorService executor; //线程池服务对象
    private static boolean isRunnable; //是否可以运行
    private static boolean isRun; //是否在运行

    private Runnable runnable;
    private long interval;
    private long nextStamp;
    private Runnable callback;
    private boolean nextFromNow;

    private TimeLock(long interval,Runnable r,long nextStamp) {
        setRunnable(r);
        this.interval = interval;
        this.nextStamp = nextStamp;
    }

    public boolean isNextFromNow() {
        return nextFromNow;
    }

    public void setNextFromNow(boolean nextFromNow) {
        this.nextFromNow = nextFromNow;
    }

    public void setRunnable(Runnable r) {
        if(r == null) {
            runnable = null;
            return;
        }
        this.runnable = () -> {
            r.run();
            this.next();
            if(this.callback != null) {
                callback.run();
            }
        };
    }

    public void nextIn(long stamp) {
        nextStamp = stamp;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getNextStamp() {
        return nextStamp;
    }

    public Runnable getCallback() {
        return callback;
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    /**
     * 设置下次执行时间
     */
    private void next() {
        if(nextFromNow) {
            fromNow(interval);
        } else {
            next(interval);
        }
    }

    public void next(long next) {
        this.nextStamp += next;
    }

    public void fromNow(long next) {
        this.nextStamp = System.currentTimeMillis() + next;
    }

    private boolean ready() {
        return System.currentTimeMillis() >= nextStamp;
    }

    private long toNext() {
        return nextStamp - System.currentTimeMillis();
    }

    @Override
    public int compareTo(TimeLock o) {
        return Long.compare(nextStamp,o.nextStamp);
    }

    /**
     *
     * @param interval 任务执行间隔
     * @param run 需执行的任务
     * @param immediately 是否立即执行
     * @return
     */
    public static synchronized TimeLock add(long interval,Runnable run,boolean immediately) {
        if(interval < 1) {
            throw new IllegalArgumentException("time wait to short ==> interval: " + interval);
        }
        long nextStamp = immediately ? System.currentTimeMillis() : System.currentTimeMillis() + interval;
        TimeLock lock = new TimeLock(interval,run,nextStamp);
        add(lock);
        return lock;  //具体执行对象
    }

    private static synchronized void add(TimeLock lock) {
        synchronized (queue) {
            queue.add(lock);
            queue.notify();  //释放正在等待的线程
        }
    }

    public static synchronized TimeLock add(long interval,Runnable run,long startStamp) {
        long cur = System.currentTimeMillis();
        if(startStamp < cur) {
            startStamp = cur;
        }
        TimeLock lock = new TimeLock(interval,run,startStamp);
        add(lock);
        return lock;
    }

    public static synchronized TimeLock dayLock(Runnable run,int hour,int minutes,int seconds) {
        long nextStamp;
        long interval = 60 * 60 * 24 * 1000;
        LocalTime now = LocalTime.now();
        LocalTime start = LocalTime.of(hour,minutes,seconds);
        if(start.isAfter(now)) {
            nextStamp = getDateTimeStamps(LocalDateTime.of(LocalDate.now(),start));
        } else {
            nextStamp = getDateTimeStamps(LocalDateTime.of(LocalDate.now().plusDays(1),start));
        }
        return add(interval,run,nextStamp);
    }

    private static long getDateTimeStamps(LocalDateTime time) {
        return time.toEpochSecond(ZoneOffset.of("+8")) * 1000;
    }

    public static synchronized TimeLock hourLock(Runnable run,int minutes,int seconds) {
        long nextStamp;
        long interval = 60 * 60 * 1000;
        LocalTime now = LocalTime.now();
        LocalTime start = now.withMinute(minutes).withSecond(seconds);
        if(start.isAfter(now)) {
            nextStamp = getDateTimeStamps(LocalDateTime.of(LocalDate.now(),start));
        } else {
            nextStamp = getDateTimeStamps(LocalDateTime.of(LocalDate.now(),start.plusHours(1)));
        }
        return add(interval,run,nextStamp);
    }

    public static synchronized TimeLock minuteLock(Runnable run,int seconds) {
        long nextStamp;
        long interval = 60 * 1000;
        LocalTime now = LocalTime.now();
        LocalTime start = now.withSecond(seconds);
        if(start.isAfter(now)) {
            nextStamp = getDateTimeStamps(LocalDateTime.of(LocalDate.now(),start));
        } else {
            nextStamp = getDateTimeStamps(LocalDateTime.of(LocalDate.now(),start.plusMinutes(1)));
        }
        return add(interval,run,nextStamp);
    }

    public static synchronized TimeLock add(int count, TimeUnit unit,Runnable run,long delay) {
        long interval = unit.toMillis(count);
        long cur = System.currentTimeMillis();
        long startStamp;
        if(cur % interval > delay) {
            startStamp = (cur / interval + 1) * interval + delay;
        } else {
            startStamp = (cur / interval) * interval + delay;
        }
        return add(interval,run,startStamp);

    }

    public static void main(String[] args) throws InterruptedException {
        dayLock(()-> System.out.println("fuck"),18,01,55);
        hourLock(()-> System.out.println("fuck2"),01,55);
        minuteLock(()-> System.out.println("fuck3"),55);
        add(1,TimeUnit.MINUTES,()-> System.out.println("fuck4"),49000);
/*        Object o = new Object();
        synchronized (o) {
            o.wait(0);
        }*/
        System.out.println("start");
        start();
    }

    /**
     * 开始执行
     */
    public synchronized static void start() {
        if(isRun && isRunnable) {
//            throw new IllegalArgumentException("task has been running");
            return;
        }
        executor = Executors.newSingleThreadExecutor();
        run();
    }

    /**
     * 移除定时任务
     * @param lock 需要移除的执行对象
     * @return
     */
    public static boolean remove(TimeLock lock) {
        return queue.remove(lock);
    }

    /**
     * 强制停止
     */
    public static void shutdown() {
        isRunnable = false;
        if(queue.size() == 0) {
            add(0,null,true);
        }
        executor.shutdown();
        queue.clear();
        isRun = false;
    }

    /**
     * 关闭所有任务，于shutdown不同的是会等待当前任务执行完毕且不会清空任务队列
     */
    public static void stop() {
        isRunnable = false;
        if(queue.size() == 0) {
            add(0,null,true); //释放被阻塞的任务队列
        }
        synchronized (queue) {
            queue.notifyAll(); //释放等待的线程
            if(isRun) {
                try {
                    queue.wait(); //等待任务执行结束
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        executor.shutdown();
    }

    /**
     * 主要执行逻辑
     */
    private static void run() {
        isRunnable = true;
        executor.execute(()->{ //使用单个线程完成定时任务
            isRun = true;
            logger.info(String.format("TimeLock[executor:%s,queueSize:%d] ===> start",executor,queue.size()));
            while (isRunnable) {
                TimeLock lock = null;
                try {
                    lock = queue.take(); //等待阻塞队列，使用优先级队列保证获取的是间隔最近的任务
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                assert lock != null;
                if(lock.runnable == null) {
                    continue;
                }
                if(!lock.ready()) {
                    synchronized (queue) {
                        try {
                            System.out.println("wait " + lock.toNext() + " ms");
                            queue.wait(lock.toNext()); //未达到执行条件，等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    lock.runnable.run(); //达到执行条件，执行任务
//                    lock.next(); //设置下次执行时间
                }
                queue.add(lock); //将任务放回任务队列
            }
            isRun = false;
            synchronized (queue) {
                queue.notifyAll();
            }
            logger.info(String.format("TimeLock[executor:%s,queueSize:%d] ===> stop",executor,queue.size()));
        });
    }

    public static boolean isRunnable() {
        return isRunnable;
    }

    public static boolean isRun() {
        return isRun;
    }

}
