package top.ivan.griddle.loader;

import top.ivan.griddle.HtmlLoader;
import top.ivan.griddle.IXHtml;

import java.io.IOException;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/31
 */
public class ProxyHtmlLoader implements HtmlLoader {
    @Override
    public IXHtml loaderHtml(String url) {
        return null;
    }

    @Override
    public IXHtml loaderHtml(String url, Map<String, String> heads) throws IOException {
        return null;
    }

    @Override
    public IXHtml loaderHtml(String url, Map<String, String> heads, Map<String, String> cookies) throws IOException {
        return null;
    }
}
