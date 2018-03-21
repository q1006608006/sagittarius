package top.ivan.sagittarius.screen.operator.spread;

import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.task.Callback;

public interface SpreadOperator {
    int accept(Seed seed, SeedContext context);
    Seed take() throws InterruptedException;
}
