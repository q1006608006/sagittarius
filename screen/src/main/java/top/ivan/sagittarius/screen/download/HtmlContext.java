package top.ivan.sagittarius.screen.download;

import top.ivan.sagittarius.screen.Site;

import java.util.List;
import java.util.Map;

public class HtmlContext {
    private String body;
    private String baseUrl;
    private String url;
    private Map<String, List<String>> headMap;
    private Map<String,String> cookies;
    private int code;

    public HtmlContext(String url,Site site) {
        this.url = url;
        this.headMap = site.getHead();
        this.cookies = site.getCookie();
//        setCookies(cookies);
    }

/*    public void setCookies(Map<String,String> cookies) {

    }*/

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, List<String>> getHeadMap() {
        return headMap;
    }

    public String getBody() {
        return body;
    }

    public List<String> getHeadValue(String head) {
        return headMap.get(head);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setHeadMap(Map<String, List<String>> heads) {
        this.headMap = heads;
    }

    public Map<String, List<String>> getHeads() {
        return headMap;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public int getCode() {
        return code;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
