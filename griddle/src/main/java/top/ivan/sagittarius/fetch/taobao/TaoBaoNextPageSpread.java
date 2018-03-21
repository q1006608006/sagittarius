package top.ivan.sagittarius.fetch.taobao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.fetch.redis.RedisSetOperator;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.operator.spread.SpreadOperator;

@Component("tbNextPageSpread")
public class TaoBaoNextPageSpread implements SpreadOperator {

    @Autowired
    private RedisSetOperator redisSetOperator;
    private static final String listKey = "nextPageSpread";
    @Override
    public int accept(Seed seed, SeedContext context) {
        TaoBaoPageBean bean = (TaoBaoPageBean) seed.getBaggage();
        try{
            if(TaoBaoBeanCheckUtils.hasNextPage(bean) || TaoBaoBeanCheckUtils.hasGridPage(bean)) {
                String nextUrl = seed.getUrl();
                nextUrl = TaoBaoBeanCheckUtils.hasNextPage(bean) ?
                        TaoBaoBeanCheckUtils.nextUrl(nextUrl, TaoBaoBeanCheckUtils.nextItemPagePos(bean)) :
                        TaoBaoBeanCheckUtils.nextUrl(nextUrl, TaoBaoBeanCheckUtils.nextInnerPagePos(bean));
                if(null != nextUrl) {
                    Seed newSeed = seed.newSeed(nextUrl, seed.getSpread());
                    redisSetOperator.add(listKey,newSeed);
                    return 1;
                }
            }
            System.out.println(seed.toString() + " has 0 items,throw it");
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Seed take() throws InterruptedException {
        return (Seed) redisSetOperator.pop(listKey);
    }
}
