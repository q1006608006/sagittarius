package top.ivan.sagittarius.screen.parse.focus;

import com.google.gson.reflect.TypeToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public class CssFocus implements Focus {

    private static final long serialVersionUID = -757189335574402597L;

    /**
     * css selector
     *
     * @param src
     * @param target jsoup - selector
     * @param key    attribute or list:attribute(json list),if accept with 'list:' then return value may struct by json else values may be appended by '\n'
     * @return
     */
    @Override
    public String peek(String src, String target, String key) throws Exception {
        Document doc = Jsoup.parse(src);
        Elements elements = doc.select(target);
        if("ignore()".equals(key)) {
            if(null != elements) {
                elements.remove();
            }
            return doc.outerHtml();
        }
        return anyKey(elements, key);
    }

    private String anyKey(Elements els, String key) throws Exception {
        if (null == key || "".equals(key) || !key.startsWith("list:")) {
            StringBuilder sb = new StringBuilder();
            ListFocus.foreach(els.toArray(), o -> sb.append(anyElement((Element) o, key)).append("\n"));
            sb.deleteCharAt(sb.lastIndexOf("\n"));
            return sb.toString();
        }
        String listKey = key.replace("list:", "");
        List list = new ArrayList();
        ListFocus.foreach(els.toArray(), o -> list.add(anyElement((Element) o, listKey)));
        return JsonFocus.toJson(list);
    }

    private String anyElement(Element element, String key) {
        String ret;
        if (null == key || "".equals(key)) {
            ret = element.outerHtml();
        } else if ("text()".equals(key)) {
            ret = element.text();
        } else {
            ret = takeAttribute(element,key);
        }
        return TestFocus.nullValue(ret);
    }

    private String takeAttribute(Element element,String key) {
        String[] keys = key.split(",");
        for (String s : keys) {
            if(element.hasAttr(s)) {
                return element.attr(s);
            }
        }
        return null;
    }


}
