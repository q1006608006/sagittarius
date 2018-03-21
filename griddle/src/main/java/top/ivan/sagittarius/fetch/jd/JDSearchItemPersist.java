package top.ivan.sagittarius.fetch.jd;

import com.google.gson.reflect.TypeToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.fetch.service.ProductService;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.operator.persist.PersistOperator;
import top.ivan.sagittarius.screen.parse.focus.JsonFocus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("jdItemPersist")
public class JDSearchItemPersist implements PersistOperator {

    private static final Type listType = new TypeToken<List<String>>() {
    }.getType();
    private static final Type jsonListType = new TypeToken<List<Map<String, String>>>() {
    }.getType();

    @Autowired
    private ProductService service;

    @Override
    public void process(Seed item) {
        long cur = System.currentTimeMillis();
        String itemListStr = item.getStorage().get("itemList");
        List<String> itemList = JsonFocus.fromJson(itemListStr, List.class);
        List<ProductPreview> beanList = itemList.stream().map(this::parseBean).collect(Collectors.toList());
        itemList = beanList.stream().map(preview -> preview.getNid().toString()).collect(Collectors.toList());
        item.getStorage().put("idList",String.join(",",itemList));
        service.updateOrInsertProductPreview(beanList);
        System.out.println(String.format("#jd-search_item-persist end,cos %dms,persist %d items",System.currentTimeMillis() - cur,beanList.size()));
    }

    private ProductPreview parseBean(String html) {
        Document doc = Jsoup.parse(html);
        ProductPreview preview = new ProductPreview();
        String itemId = doc.select("li").attr("data-sku");
        String imgUrl = doc.select(".err-product").attr("src");
        if(StringUtil.isBlank(imgUrl)) {
            imgUrl = doc.select(".err-product").attr("data-lazy-img");
        }
        imgUrl = "https:" + imgUrl;
        String price;
        price = doc.select(".p-price [data-done]").get(0).text().replace("￥", "");
        if (StringUtil.isBlank(price)) {
            price = doc.select(".p-price [data-done]").get(0).attr("data-price");
        }
        String title = doc.select(".p-name em").get(0).text();
        String commit = doc.select(".p-commit strong").get(0).text();
        String shopBody = doc.select(".p-shop").get(0).outerHtml();
        preview.setNid(Long.valueOf(itemId));
        preview.setPicUrl(imgUrl);
        preview.setViewPrice(new BigDecimal(price));
        preview.setTitle(title);
        preview.setCommentCount(getCommentCount(commit));
        fixShop(preview,shopBody);
        preview.setViewSales("-1");
        preview.setLocation("jd");
        preview.setDetailUrl(String.format("https://item.jd.com/%s.html",itemId));
        return preview;
    }

    private int getCommentCount(String commit) {
        int sz = 1;
        if(commit.contains("万")) {
            sz = 10000;
        }
        commit = commit.replaceAll("[^.\\d]","");
        Double ret = Double.valueOf(commit);
        ret = ret * sz;
        return ret.intValue();
    }

    private void fixShop(ProductPreview bean, String shopBody) {
        Document doc = Jsoup.parse(shopBody);
        Elements shopEls = doc.select("a");
        if (shopEls == null || shopEls.size() == 0) {
            String shopId = doc.select(".p-shop").attr("data-shopid");
            if (null != shopId && shopId.length() > 0) {
                bean.setShopLink("https://mall.jd.com/index-" + shopId + ".html");
                try {
                    String title = getAjaxShopName(shopId);
                    bean.setNick(title);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                bean.setNick("京东自营");
                bean.setShopLink("https://www.jd.com");
            }
        } else {
            bean.setShopLink("https:" + shopEls.attr("href"));
            bean.setNick(shopEls.text());
        }

    }


    private String getAjaxShopName(String shopId) throws IOException {
        for (int i = 0; i < 5; i++) {
            try {
                Connection connection = Jsoup.connect(String.format("https://search.jd.com/shop_new.php?ids=%s", shopId));
                connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36");
                connection.header("Referer", "https://search.jd.com/Search");
                String body = connection.execute().body();
                List<Map<String, String>> jsonBean = JsonFocus.fromJson(body, jsonListType);
                return jsonBean.get(0).get("shop_name");
            } catch (Exception ignored) {
            }
        }
        System.out.println(String.format("can not resolve shop name with shopId:%s", shopId));
        return null;
    }
}
