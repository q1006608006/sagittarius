package top.ivan.sagittarius.fetch;

public class UrlUtil {
    public static String getParamValue(String url, String param) {
        url += "&";
        String regex = String.format(".*[&?]%s=(.*?)&.*",param);
        if(hasParam(url,param)) {
            return url.replaceAll(regex,"$1");
        }
        return null;
    }

    public static String getParamValueOrDefault(String url, String param,String def) {
        String r = getParamValue(url,param);
        return null == r ? def : r;
    }

    public static boolean hasParam(String url,String param) {
        String regex = String.format(".*[&?]%s=.*",param);
        return url.matches(regex);
    }

    public static String setParamValue(String url,String param,String value) {
        if(hasParam(url,param)) {
            if(url.matches(String.format(".*[&?]%s=.*&.*",param))) {
                String regex = String.format("([&?]%s)=.*&",param);
                return url.replaceAll(regex,String.format("$1=%s&",value));
            } else {
                return url.replaceAll(String.format("([&?]%s)=.*",param),"$1=" + value);
            }
        }
        return String.format("%s%s%s=%s",url,url.contains("?") ? "&" : "?" , param,value);
    }

    public static void main(String[] args) {
        String url = "https://search.jd.com/s_new.php?keyword=%E9%BC%A0%E6%A0%87%E5%9E%AB&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&scrolling=y&log_id=1521553056.48426&tpl=1_M&page=4&ss=60";
        System.out.println(setParamValue(url,"ss","13"));
    }

}
