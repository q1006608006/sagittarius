package top.ivan.griddle.focus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import top.ivan.griddle.Focus;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public class CssFocus implements Focus {

    /**
     * css selector
     *
     * @param src
     * @param target jsoup - selector
     * @param key    attribute or list:attribute(json list),if start with 'list:' then return value may struct by json else values may be appended by '\n'
     * @return
     */
    @Override
    public String peek(String src, String target, String key) throws Exception {
        Elements elements = Jsoup.parse(src).select(target);
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
            ret = element.attr(key);
        }
        return TestFocus.nullValue(ret);
    }

}
