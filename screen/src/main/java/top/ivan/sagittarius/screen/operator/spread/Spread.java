package top.ivan.sagittarius.screen.operator.spread;

import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.PartsManager;
import top.ivan.sagittarius.screen.context.SeedContext;

import java.util.List;

public interface Spread {
    void spread(Seed seed, List<SpreadWallet> spreadWallets,SeedContext context);
    void listen(SpreadWallet wallet,SeedContext context);
    void stop();
}
