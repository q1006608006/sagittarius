package top.ivan.sagittarius.screen.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class TimeLock implements Comparable <TimeLock> {
    private static Logger logger = LogManager.getLogger(TimeLock.class);

    private static final PriorityBlockingQueue<TimeLock> queue = new PriorityBlockingQueue<>();  //优先级队列，用于保存任务
    private static ExecutorService executor; //线程池服务对象
    private static boolean isRunnable; //是否可以运行
    private static boolean isRun; //是否在运行

    private Runnable runnable;
    private long interval;
    private long nextStamp;



    private TimeLock(long interval,Runnable r,boolean immediately) {
        this.runnable = r;
        this.interval = interval;
        if(!immediately) {
            nextStamp = System.currentTimeMillis() + interval;
        }
    }

    /**
     * 设置下次执行时间
     */
    private void next() {
        nextStamp = System.currentTimeMillis() + interval;
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
        TimeLock lock = new TimeLock(interval,run,immediately);
        synchronized (queue) {
            queue.add(lock);
            queue.notify();  //释放正在等待的线程
        }
        return lock;  //具体执行对象
    }

    /**
     * 开始执行
     */
    public synchronized static void start() {
        if(isRun && isRunnable) {
            throw new IllegalArgumentException("task has been running");
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
                if(lock.nextStamp > System.currentTimeMillis()) {
                    synchronized (queue) {
                        try {
                            queue.wait(lock.nextStamp - System.currentTimeMillis()); //未达到执行条件，等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    lock.runnable.run(); //达到执行条件，执行任务
                    lock.next(); //设置下次执行时间
                }
                queue.add(lock); //将任务放任务队列
            }
            isRun = false;
            synchronized (queue) {
                queue.notifyAll();
            }
            logger.info(String.format("TimeLock[executor:%s,queueSize:%d] ===> stop",executor,queue.size()));
        });
    }

    public Runnable isRunnable() {
        return runnable;
    }

    public static boolean isRun() {
        return isRun;
    }

}
