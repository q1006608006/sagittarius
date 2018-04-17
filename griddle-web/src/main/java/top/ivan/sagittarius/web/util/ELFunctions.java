package top.ivan.sagittarius.web.util;

import com.google.gson.Gson;
import top.ivan.sagittarius.griddle.persist.pojo.ProductPreview;

public class ELFunctions {
    private static final Gson GSON = new Gson();

    public static String toJson(Object o) {
        return GSON.toJson(o);
    }

    public static String fixLocation(String location) {
        if("tmall".equalsIgnoreCase(location)) {
            return "天猫";
        } else if("taobao".equalsIgnoreCase(location)) {
            return "淘宝";
        } else if("jd".equalsIgnoreCase(location)) {
            return "京东";
        } else if("gm".equalsIgnoreCase(location)) {
            return "国美";
        }
        return "未知";
    }

    public static String getLocationUrl(String location) {
        if("tmall".equalsIgnoreCase(location)) {
            return "//www.tmall.com";
        } else if("taobao".equalsIgnoreCase(location)) {
            return "//www.taobao.com";
        } else if("jd".equalsIgnoreCase(location)) {
            return "//www.jd.com";
        } else if("gm".equalsIgnoreCase(location)) {
            return "//www.gome.com.cn";
        }
        return "//www." + location + ".com";
    }
}
