package top.ivan.sagittarius.fetch.parts;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.griddle.persist.dao.SearchDao;
import top.ivan.sagittarius.griddle.persist.pojo.Search;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.operator.spread.ContextAware;
import top.ivan.sagittarius.screen.operator.spread.SpreadOperator;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component("b1bjSpread")
public class B1BJSpread implements SpreadOperator, InitializingBean, ContextAware {
    @Autowired
    private SearchDao searchDao;
    private SeedContext context;
    private List<Search> searchList;
    private BlockingQueue<Seed> blockingQueue = new LinkedBlockingQueue<>();
    private Set<String> urlSet = new TreeSet<>();
    private boolean isRun = false;

    @Override
    public int accept(Seed seed, SeedContext context) {
//        blockingQueue.add(seed);
        if(urlSet.add(seed.getUrl())) {
            blockingQueue.add(seed);
        }
        if(!isRun) {
            start(seed);
            isRun = true;
        }
        return 0;
    }

    private void start(Seed seed) {
        for (Search search : searchList) {
            Seed newSeed = getSeedByKey(seed, search.getSearchKey());
            blockingQueue.add(newSeed);
        }
    }

    private Seed getSeedByKey(Seed src, String key) {
        Seed seed = src.copy();
        seed.setUrl("http://b1bj.com/s.aspx?key=" + key);
        return seed;
    }

    @Override
    public Seed take() throws InterruptedException {
        return blockingQueue.take();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        searchList = searchDao.all(0, 3);
    }

    @Override
    public void setSeedContext(SeedContext context) {
        this.context = context;
    }
}
