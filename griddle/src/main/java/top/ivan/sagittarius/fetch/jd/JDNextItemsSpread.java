package top.ivan.sagittarius.fetch.jd;

import org.springframework.stereotype.Component;
import top.ivan.sagittarius.fetch.UrlUtil;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.operator.spread.SpreadOperator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component("jdNextSearchSpread")
public class JDNextItemsSpread implements SpreadOperator {
    private BlockingQueue<Seed> seedBlockingQueue = new LinkedBlockingQueue<>();
    @Override
    public int accept(Seed seed, SeedContext context) {
        Integer nextIndex = Integer.valueOf(UrlUtil.getParamValueOrDefault(seed.getUrl(),"page","1")) + 2;
        if(nextIndex >= 200) {
            return 0;
        }
        String countStr = seed.getStorage().getOrDefault("totalCount","0").replaceAll("万","0000").replaceAll("[^\\d]","");
        if(nextIndex * 60 > Integer.valueOf(countStr)) {
            return 0;
        }
        String url = UrlUtil.setParamValue(seed.getUrl(),"page",nextIndex.toString());
        url = UrlUtil.setParamValue(url,"s",String.valueOf((nextIndex - 1) * 30));
        seedBlockingQueue.add(seed.newSeed(url,seed.getSpread()));
        return 1;
    }

    @Override
    public Seed take() throws InterruptedException {
        return seedBlockingQueue.take();
    }

    public static int getCurPageByUrl(String url) {
        if(url.contains("&page=") || url.contains("?page=")) {
            String index = url.replaceAll(".*[&?]page=(\\d*).*","$1");
            if(index.length() > 0) {
                return Integer.valueOf(index);
            }
        }
        return 1;
    }

    public static String getPageUrl(String url,int index) {
        if(url.contains("&page=") || url.contains("?page=")) {
            return url.replaceAll("([?&]page)=\\d*", "$1=" + index);
        }
        return String.format("%s&page=%d",url,index);
    }



    public static void main(String[] args) {
        String url = "https://search.jd.com/s_new.php?keyword=%E9%BC%A0%E6%A0%87%E5%9E%AB&enc=utf-8&qrst=1&rt=1&stop=1&vt=2?page=11";
        System.out.println(getPageUrl(url,getCurPageByUrl(url)));
    }
}
