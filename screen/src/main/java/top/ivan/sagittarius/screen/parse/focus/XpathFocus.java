package top.ivan.sagittarius.screen.parse.focus;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IVAN on 2017/10/21.
 */
public class XpathFocus implements Focus {
    private static final long serialVersionUID = 3431452335006643159L;

    /**
     * @param src
     * @param target xpath target
     * @param key    if accept with 'list:' then return value may struct by json else values may be appended by '\n'
     * @return list of attribute
     * @throws XPatherException
     */
    @Override
    public String peek(String src, String target, String key) throws Exception {
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode tagNode = cleaner.clean(src);
        Object[] objs = tagNode.evaluateXPath(target);
        return anyKey(objs, key);
    }

    public String anyKey(Object[] nodes, String key) throws Exception {
        if (null == key || "".equals(key) || !key.startsWith("list:")) {
            StringBuilder sb = new StringBuilder();
            ListFocus.foreach(nodes, o -> sb.append(anyNode((TagNode) o, key)).append("\n"));
            sb.deleteCharAt(sb.lastIndexOf("\n"));
            return sb.toString();
        }
        String listKey = key.replace("list:", "");
        List list = new ArrayList();
        ListFocus.foreach(nodes, o -> list.add(anyNode((TagNode) o, listKey)));
        return JsonFocus.toJson(list);
    }

    public String anyNode(TagNode node, String key) {
        String ret;
        if (null == key || "".equals(key) || "text()".equals(key)) {
            ret = node.getText().toString();
        } else {
            ret = node.getAttributeByName(key);
        }
        return TestFocus.nullValue(ret);
    }

}
