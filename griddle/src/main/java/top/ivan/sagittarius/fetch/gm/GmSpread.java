package top.ivan.sagittarius.fetch.gm;

import com.alibaba.dubbo.common.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.fetch.UrlUtil;
import top.ivan.sagittarius.griddle.persist.dao.SearchDao;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.operator.spread.PositiveSpreadOperator;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component("gmSpread")
public class GmSpread implements PositiveSpreadOperator {

    @Autowired
    private SearchDao searchDao;
    private BlockingQueue<Seed> seedQueue = new LinkedBlockingQueue<>();

    @Override
    public void onActive(SeedContext context) {
        List<String> keyList = searchDao.getAllKey();
        for (String key : keyList) {
            Seed seed = new Seed(context, "https://search.gome.com.cn/search?question=" +
                    URL.encode(key) +
                    "&searchType=goods&&page=1&type=json", "item");
            this.seedQueue.add(seed);
        }
    }

    @Override
    public int accept(Seed seed, SeedContext context) {
        if (GmUtils.isFinished(seed)) {
            System.out.println(String.format("'%s' is the last seed url,finished!"));
            return 0;
        }
        String nextUrl = seed.getUrl();
        String indexStr = UrlUtil.getParamValueOrDefault(nextUrl, "page", "1");
        nextUrl = UrlUtil.setParamValue(nextUrl, "page", (Integer.valueOf(indexStr) + 1) + "");
        Seed next = seed.newSeed(nextUrl, seed.getSpread());
        seedQueue.add(next);
        return 1;
    }

    @Override
    public Seed take() throws InterruptedException {
        return seedQueue.take();
    }
}
