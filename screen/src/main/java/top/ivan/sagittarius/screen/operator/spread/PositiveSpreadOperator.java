package top.ivan.sagittarius.screen.operator.spread;

import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.SeedContext;

public interface PositiveSpreadOperator extends SpreadOperator {
    void onActive(SeedContext context);
    default int accept(Seed seed,SeedContext context) {
        return 0;
    }
}
