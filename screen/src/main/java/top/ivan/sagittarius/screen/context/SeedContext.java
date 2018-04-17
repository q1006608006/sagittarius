package top.ivan.sagittarius.screen.context;

import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.Site;
import top.ivan.sagittarius.screen.Rule;
import top.ivan.sagittarius.screen.operator.spread.SpreadWallet;

import java.util.List;
import java.util.Map;

public interface SeedContext {

    String getLocation();

    Site getSite();

    List<Seed> getBaseSeeds();

    Map<String,Rule> getRuleMap();

    List<SpreadWallet> getActiveSpreads();

    long getActiveTime();
}
