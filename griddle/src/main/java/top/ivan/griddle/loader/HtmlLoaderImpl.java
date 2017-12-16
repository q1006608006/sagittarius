package top.ivan.griddle.loader;

import okhttp3.*;
import top.ivan.griddle.HtmlLoader;
import top.ivan.griddle.IXHtml;

import java.io.IOException;
import java.util.*;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/31
 */
public class HtmlLoaderImpl implements HtmlLoader {
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    private OkHttpClient client = new OkHttpClient.Builder().cookieJar(new CookieJar() {
        @Override
        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
            cookieStore.put(httpUrl.host(), list);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
            List<Cookie> cookies = cookieStore.get(httpUrl.host());
            return cookies == null ? new ArrayList<>() : cookies;
        }
    }).build();

    @Override
    public IXHtml loaderHtml(String url) throws IOException {
        return loaderHtml(new Request.Builder().url(url).build());
    }

    @Override
    public IXHtml loaderHtml(String url, Map<String, String> heads) throws IOException {
        Request.Builder builder = new Request.Builder().url(url);
        buildHead(heads,builder);
        return loaderHtml(builder.build());
    }

    @Override
    public IXHtml loaderHtml(String url, Map<String, String> heads, Map<String, String> cookies) throws IOException {
        Request.Builder builder = new Request.Builder().url(url);
        buildHead(heads,builder);
        return null;
    }

    private void buildHead(Map<String,String> heads,Request.Builder builder) {
        Iterator<Map.Entry<String, String>> ihead = heads.entrySet().iterator();
        Map.Entry<String, String> entry;
        Headers.Builder headBuilder = new Headers.Builder();
        while (ihead.hasNext()) {
            entry = ihead.next();
            headBuilder.add(entry.getKey(), entry.getValue());
        }
        builder.headers(headBuilder.build());
    }


    public IXHtml loaderHtml(Request request) throws IOException {
        Call call = client.newCall(request);
        Response response = call.execute();
        IXHtml ixHtml = new IXHtml();
        ixHtml.setBody(response.body().string());
        ixHtml.setCode(response.code());
        ixHtml.setHeadMap(response.headers().toMultimap());
        return ixHtml;
    }
}
