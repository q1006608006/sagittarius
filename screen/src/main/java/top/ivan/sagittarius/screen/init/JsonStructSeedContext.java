package top.ivan.sagittarius.screen.init;

import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.Site;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.Rule;
import top.ivan.sagittarius.screen.operator.spread.SpreadWallet;

import java.util.List;
import java.util.Map;

public class JsonStructSeedContext implements SeedContext {

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public Site getSite() {
        return null;
    }

    @Override
    public List<Seed> getBaseSeeds() {
        return null;
    }


    @Override
    public Map<String, Rule> getRuleMap() {
        return null;
    }

    @Override
    public List<SpreadWallet> getActiveSpreads() {
        return null;
    }
}
