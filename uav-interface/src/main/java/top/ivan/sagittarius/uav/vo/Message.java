package top.ivan.sagittarius.uav.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
    private Map<String,String> stores;

    public Message() {
        stores = new HashMap<>();
    }

    public Message(Map<String,String> map) {
        stores = map;
    }

    public String takeInfo(String param) {
        return stores.get(param);
    }

    public void putInfo(String param, String value) {
        stores.put(param,value);
    }

    public Map<String,String> takeMessageStores() {
        return stores;
    }

    final void setMessageStores(Map<String,String> stores) {
        this.stores = stores;
    }
}
