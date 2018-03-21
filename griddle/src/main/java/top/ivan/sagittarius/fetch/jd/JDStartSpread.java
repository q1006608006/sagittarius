package top.ivan.sagittarius.fetch.jd;

import com.alibaba.dubbo.common.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.griddle.persist.dao.SearchDao;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.operator.spread.ContextAware;
import top.ivan.sagittarius.screen.operator.spread.PositiveSpreadOperator;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component("jdStartSpread")
public class JDStartSpread implements PositiveSpreadOperator,ContextAware {

    private SeedContext context;
    private BlockingQueue<Seed> seedQueue = new LinkedBlockingQueue<>();

    @Autowired
    private SearchDao searchDao;

    @Override
    public void onActive(SeedContext context) {
        List<String> keyList = searchDao.getAllKey();
        for (String key : keyList) {
            Seed seed = new Seed(context,
                    "https://search.jd.com/s_new.php?keyword=" +
                            URL.encode(key) +
                            "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&scrolling=y&log_id=1521553056.48426&tpl=1_M",
                    "search");
            this.seedQueue.add(seed);
        }
    }

    @Override
    public Seed take() throws InterruptedException {
        return seedQueue.take();
    }

    @Override
    public void setSeedContext(SeedContext context) {
        this.context = context;
    }
}
