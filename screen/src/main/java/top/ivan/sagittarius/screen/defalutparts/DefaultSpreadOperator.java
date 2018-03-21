package top.ivan.sagittarius.screen.defalutparts;

import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.operator.spread.SpreadOperator;
import top.ivan.sagittarius.screen.task.Callback;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DefaultSpreadOperator implements SpreadOperator {
    private BlockingQueue<Seed> seedBlockingQueue;

    public DefaultSpreadOperator() {
        seedBlockingQueue = new LinkedBlockingQueue<>(Integer.MAX_VALUE);
    }


    @Override
    public int accept(Seed seed, SeedContext context) {
        return seedBlockingQueue.add(seed) ? 1 : 0;
    }

    @Override
    public Seed take() throws InterruptedException {
        return seedBlockingQueue.take();
    }
}
