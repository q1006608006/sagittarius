package top.ivan.sagittarius.fetch.taobao;

import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.parse.focus.JsonFocus;

public class TaoBaoBeanCheckUtils {

    public static boolean checkUrlEqual(TaoBaoPageBean bean) {
        return null != bean.getBaseUrl() && null != bean.getUrl() && bean.getUrl().equals(bean.getBaseUrl());
    }
    public static boolean hasNextPage(TaoBaoPageBean bean) {
        if(!checkUrlEqual(bean)) {
            return false;
        }
        TaoBaoPageBean.ModsBean.ItemlistBean itemlist = bean.getMods().getItemlist();
        return itemlist != null && itemlist.getData() != null && itemlist.getData().getAuctions() != null && itemlist.getData().getAuctions().size() >= 1;
    }

    public static boolean hasProductItems(TaoBaoPageBean bean) {
        try {
            return bean.getMods().getItemlist().getData().getAuctions().size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean hasGridPage(TaoBaoPageBean bean) {
        TaoBaoPageBean.ModsBean.GridBean grid = bean.getMods().getGrid();
        return grid != null && grid.getData().getSpus() != null && grid.getData().getSpus().size() >= 1;
    }

    public static String nextUrl(String url,int pos) {
        int startPos = 0;
        if(url.contains("&s=") || url.contains("?s=")) {
            String start = url.replaceAll(".+(\\?s=|&s=)([0-9]+)(&.*)?", "$2");
            startPos = Integer.valueOf(start);
        }
        int signPos = url.lastIndexOf("?");
        startPos += pos;
        if(url.contains("&s=") || url.contains("&s=")) {
            return url.replaceAll("([&?])s=\\d+","$1s=" + startPos);
        } else {
            return url + (signPos > 0 ? "&":"?") + "s=" + startPos;
        }

    }

    public static int nextItemPagePos(TaoBaoPageBean bean) {
        return bean.getMods().getItemlist().getData().getAuctions().size();
    }

    public static int nextInnerPagePos(TaoBaoPageBean bean) {
        return bean.getMods().getGrid().getData().getSpus().size();
    }

    public static TaoBaoPageBean fromSeed(Seed seed,String key) {
        try {
            String src = seed.getStorage().get(key);
            TaoBaoPageBean bean = JsonFocus.fromJson(src, TaoBaoPageBean.class);
            bean.setUrl(seed.getUrl());
            bean.setBaseUrl(seed.getBaseUrl());
            return bean;
        } catch (Throwable t) {
            return null;
        }
    }
}
