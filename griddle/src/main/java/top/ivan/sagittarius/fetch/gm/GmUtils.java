package top.ivan.sagittarius.fetch.gm;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.screen.Seed;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

public class GmUtils {
    private static String priceUrl = "https://ss.gome.com.cn/search/v1/price/single/${pid}/${skuid}/25010000/flag/item";
    private static Gson gson = new Gson();
    public static RedisTemplate<String,String> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String,String> redisTemplate) {
        GmUtils.redisTemplate = redisTemplate;
    }

    public static BigDecimal getPrice(String pid, String skuId) {
        for (int i = 0; i < 10; i++) {
            try {
                String url = priceUrl.replace("${pid}", pid).replace("${skuid}", skuId);
                String body = Jsoup.connect(url).
                        header("host"," ss.gome.com.cn").
                        header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36")
                        .get().text();
                Map data = gson.fromJson(body, Map.class);
                boolean success = (boolean) data.get("success");
                if(success) {
                    String price = (String) ((Map)data.get("result")).get("price");
                    return new BigDecimal(price);
                }
            } catch (Exception ignored) {
            }
        }
        return BigDecimal.ZERO;
    }

    public static ProductPreview transForPreview(GmPreviewBean bean) {
        ProductPreview preview = new ProductPreview();
        preview.setLocation("gm");
        preview.setViewPrice(bean.getPrice());
        preview.setCommentCount(bean.getEvaluateCount());
        preview.setViewSales(String.valueOf(bean.getSalesVolume()));
        preview.setDetailUrl("https:" + bean.getSUrl());
        preview.setNid((long) preview.getDetailUrl().hashCode());
        preview.setTitle(bean.getName().replaceAll("<.*?>",""));
        if(null != bean.getMUrl()) {
            preview.setShopLink("https:" + bean.getMUrl());
            preview.setNick(bean.getSName());
        } else {
            preview.setShopLink("https://www.gome.com.cn");
            preview.setNick("国美自营");
        }
        preview.setCategory(bean.getFirstCat());
        preview.setPicUrl("https:" + bean.getSImg());
        preview.setViewSales("-1");
        return preview;
    }

    public static boolean isFinished(Seed seed) {
        return "[]".equals(seed.getStorage().get("json"));
    }

}
