package top.ivan.sagittarius.screen;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Site implements Serializable {
    private static final long serialVersionUID = 1849698781542939409L;
    private String website;
    private Map<String,List<String>> head;
    private Map<String,String> cookie;
    private Map<String,String> storage;
    private long interval;
    private long timeout;

    public Map<String, String> getStorage() {
        return storage;
    }

    public void setStorage(Map<String, String> storage) {
        this.storage = storage;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Map<String, List<String>> getHead() {
        return head;
    }

    public void setHead(Map<String, List<String>> head) {
        this.head = head;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }

    public void setCookie(Map<String, String> cookie) {
        this.cookie = cookie;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
