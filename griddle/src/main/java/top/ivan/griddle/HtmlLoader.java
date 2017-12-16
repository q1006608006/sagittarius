package top.ivan.griddle;

import java.io.IOException;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/19
 */
public interface HtmlLoader {
    IXHtml loaderHtml(String url) throws IOException;
    IXHtml loaderHtml(String url, Map<String, String> heads) throws IOException;
    IXHtml loaderHtml(String url, Map<String, String> heads, Map<String, String> cookies) throws IOException;
}
