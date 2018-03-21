package top.ivan.sagittarius.screen.operator.persist;

import top.ivan.sagittarius.screen.Seed;

import java.util.List;

public interface Persist {
    void process(Seed item, List<PersistWallet> spreadWallets) throws Exception;
}
