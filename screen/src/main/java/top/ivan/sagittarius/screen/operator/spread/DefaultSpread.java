package top.ivan.sagittarius.screen.operator.spread;

import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.PartsManager;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.defalutparts.DefaultSpreadOperator;
import top.ivan.sagittarius.screen.operator.StopHandle;
import top.ivan.sagittarius.screen.parse.focus.JsonFocus;
import top.ivan.sagittarius.screen.parse.focus.ReplaceFocus;
import top.ivan.sagittarius.screen.task.TimeLock;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DefaultSpread implements Spread {

    private static Logger logger = LogManager.getLogger(DefaultSpread.class);
    private final SpreadHandle handle;
    private PartsManager manager;
    private final Map<SpreadOperator, Integer> operatorMap;
    private ThreadPoolExecutor executor;
    private SpreadOperator defaultOperator = new DefaultSpreadOperator();
    private boolean isRunning;

    public DefaultSpread(PartsManager manager, SpreadHandle handle) {
        this.manager = manager;
        this.handle = handle;
        operatorMap = new HashMap<>();
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        isRunning = true;
    }

    @Override
    public void spread(Seed seed, List<SpreadWallet> spreadWallets, SeedContext context) {
        if (null == spreadWallets) {
            return;
        }
        for (SpreadWallet spreadWallet : spreadWallets) {
            spreadNextSeeds(seed, spreadWallet, context);
        }
    }

    @Override
    public void listen(SpreadWallet wallet, SeedContext context) {
        SpreadOperator operator = manager.getSpreadOperator(wallet.getProcessor());
        if (null == operator) {
            throw new RuntimeException("not spread is '" + wallet.getProcessor() + "'");
        }
        synchronized (operatorMap) {
            if (operatorMap.get(operator) != null) {
                throw new RuntimeException("multi listen operator:" + operator);
            }
            operatorMap.put(operator, 0);
            if (operator instanceof ContextAware) {
                ((ContextAware) operator).setSeedContext(context);
            }
            if (wallet.getActiveInterval() > 0 && operator instanceof PositiveSpreadOperator) {
                TimeLock.add(wallet.getActiveInterval(), ()->((PositiveSpreadOperator) operator).onActive(context), true);
            }
            executor.execute(() -> {
                try {
                    while (isRunning) {
                        handle.accept(operator.take());
                        synchronized (operatorMap) {
                            int waitSize = operatorMap.get(operator);
                            operatorMap.put(operator, waitSize - 1);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            if (!TimeLock.isRun()) {
                TimeLock.start();
            }
        }
    }

    private void spreadNextSeeds(Seed seed, SpreadWallet spreadWallet, SeedContext context) {
        Map<String, String> storage = seed.getStorage();
        String listKey = spreadWallet.getList();
        if (storage == null || storage.get(listKey) == null) {
            spreadSeed(getNextSpreadSeed(seed, spreadWallet), spreadWallet, context);
        } else {
            spreadSeeds(getNextSpreadSeedList(seed, spreadWallet), spreadWallet, context);
        }
    }

    private void spreadSeeds(List<Seed> seeds, SpreadWallet spreadWallet, SeedContext context) {
        for (Seed seed : seeds) {
            spreadSeed(seed, spreadWallet, context);
        }
    }

    private List<Seed> getNextSpreadSeedList(Seed seed, SpreadWallet wallet) {
        String listKey = wallet.getList();
        String listValues = seed.getStorage().get(listKey);
        List<String> valueList;
        if (listValues.startsWith("[")) {
            valueList = JsonFocus.fromJson(listValues, new TypeToken<List<String>>() {
            }.getType());
        } else {
            valueList = Arrays.asList(listValues.split(","));
        }
        String expression = wallet.getExpression();
        for (int i = 0; i < valueList.size(); i++) {
            valueList.set(i, expression.replaceAll("\\{\\$" + listKey + "\\}", valueList.get(i)));
        }
        List<Seed> seeds = new ArrayList<>();
        for (String url : valueList) {
            url = ReplaceFocus.peeKey(url, seed.getSite().getStorage());
            Seed nextSeed = seed.newSeed(url, wallet);
            seeds.add(nextSeed);
        }
        return seeds;
    }

    private void spreadSeed(Seed seed, SpreadWallet wallet, SeedContext context) {
        if (!checkSpreadable(seed, wallet)) {
            logger.info(String.format("end spread %s ,status:[currentDeep: %d,maxDeep: %d,match: %s]", seed, seed.getGeneration(), wallet.getMaxDeep(), wallet.getMatch()));
            return;
        }
        SpreadOperator operator = manager.getSpreadOperator(wallet.getProcessor());
        if (operator == null) {
            operator = defaultOperator;
            if (null != wallet.getProcessor()) {
                logger.error("can not found spread is " + wallet.getProcessor());
                return;
            }
        }
        int count = operator.accept(seed, context);
        if (count > 0) {
            synchronized (operatorMap) {
                if (null != operatorMap.get(operator)) {
                    operatorMap.put(operator, operatorMap.get(operator) + count);
                } else {
                    addOperatorHoldHandle(operator, count);
                }
            }
        }
    }

    private void addOperatorHoldHandle(SpreadOperator operator, int count) {
        if (operatorMap.get(operator) == null) {
            operatorMap.put(operator, count);
            executor.execute(() -> {
                while (isRunning) {
                    try {
                        if (operatorMap.get(operator) < 1) {
                            break;
                        }
                        Seed seed = operator.take();
                        synchronized (operatorMap) {
                            int waitSize = operatorMap.get(operator);
                            operatorMap.put(operator, waitSize - 1);
                        }
                        handle.accept(seed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                logger.debug("end fetch with spread-operator: " + operator.toString());
                synchronized (operatorMap) {
                    operatorMap.remove(operator);
                }
            });
        }
    }

    private Seed getNextSpreadSeed(Seed seed, SpreadWallet spreadWallet) {
        String urlExpression = spreadWallet.getExpression();
        if (seed.isIndivisible() || urlExpression == null) {
            seed.setSpread(spreadWallet);
//            seed.setIndivisible(false);
            return seed;
        }
        urlExpression = ReplaceFocus.peeKey(urlExpression, seed.getStorage());
        Seed nextSeed = seed.newSeed(urlExpression, spreadWallet);
        return nextSeed;
    }

    public void stop() {
        isRunning = false;
        Set<SpreadOperator> operators = operatorMap.keySet();
        for (SpreadOperator operator : operators) {
            if (operator instanceof StopHandle) {
                ((StopHandle) operator).stop();
            }
        }
        executor.shutdown();
    }

    private boolean checkSpreadable(Seed seed, SpreadWallet wallet) {
        return (null == wallet.getMatch() || seed.getUrl().matches(wallet.getMatch())) && (wallet.getMaxDeep() <= 0 || seed.getGeneration() <= wallet.getMaxDeep());
    }

}
