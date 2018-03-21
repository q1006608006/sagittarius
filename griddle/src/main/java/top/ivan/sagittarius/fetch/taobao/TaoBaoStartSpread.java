package top.ivan.sagittarius.fetch.taobao;

import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.fetch.parts.focus.TaobaoSearchFocus;
import top.ivan.sagittarius.griddle.persist.dao.SearchDao;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.Site;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.operator.spread.ContextAware;
import top.ivan.sagittarius.screen.operator.spread.PositiveSpreadOperator;
import top.ivan.sagittarius.screen.operator.spread.SpreadOperator;
import top.ivan.sagittarius.screen.parse.focus.JsonFocus;
import top.ivan.sagittarius.screen.parse.focus.ListFocus;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component("tbStartSpread")
public class TaoBaoStartSpread implements PositiveSpreadOperator,ContextAware {
    private SeedContext context;
    private BlockingQueue<Seed> seedQueue = new LinkedBlockingQueue<>();

    @Autowired
    private SearchDao searchDao;

    @Override
    public Seed take() throws InterruptedException {
        return seedQueue.take();
    }

    @Override
    public void onActive(SeedContext context) {
        List<String> keyList = searchDao.getAllKey();
        for (String key : keyList) {
            Seed seed = new Seed(context,"https://s.taobao.com/search?q=" + key+"&cd=false","item");
            this.seedQueue.add(seed);
        }
    }

    @Override
    public void setSeedContext(SeedContext context) {
        this.context = context;
    }

}
