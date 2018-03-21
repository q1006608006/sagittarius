package top.ivan.sagittarius.fetch.jd;

import org.springframework.stereotype.Component;
import top.ivan.sagittarius.fetch.UrlUtil;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.operator.spread.SpreadOperator;
import top.ivan.sagittarius.screen.parse.focus.JsonFocus;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component("jdXhrSearchSpread")
public class JDXhrSearchSpread implements SpreadOperator {
    private BlockingQueue<Seed> seedBlockingQueue = new LinkedBlockingQueue<>();

    @Override
    public int accept(Seed seed, SeedContext context) {
        String idStr = seed.getStorage().get("idList");
//        List<String> idList = JsonFocus.fromJson(idStr,List.class);
//        idStr = String.join(",",idList);
        int page =  Integer.valueOf(UrlUtil.getParamValueOrDefault(seed.getUrl(),"page","1"));
        String url = UrlUtil.setParamValue(seed.getUrl(),"page",String.valueOf(page +1));
        url = UrlUtil.setParamValue(url,"s",String.valueOf(page * 30));
        url = UrlUtil.setParamValue(url,"show_items",idStr);
        seed = seed.newSeed(url,seed.getSpread());
        seedBlockingQueue.add(seed);
        return 1;
    }

    @Override
    public Seed take() throws InterruptedException {
        return seedBlockingQueue.take();
    }
}
