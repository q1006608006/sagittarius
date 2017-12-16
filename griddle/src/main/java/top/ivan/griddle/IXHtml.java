package top.ivan.griddle;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/19
 */
public class IXHtml {
    private String body;
    private Map<String,List<String>> headMap;
    private int code;

    public String getBody() {
        return body;
    }

    public List<String> getHeadValue(String head) {
        return headMap.get(head);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setHeadMap(Map<String,List<String>> heads) {
        this.headMap = heads;
    }

    public Map<String,List<String>> getHeads() {
        return headMap;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public int getCode() {
        return code;
    }
}
