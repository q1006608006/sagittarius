package top.ivan.sagittarius.screen.defalutparts;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.Site;
import top.ivan.sagittarius.screen.download.Downloader;
import top.ivan.sagittarius.screen.download.HtmlContext;
import top.ivan.sagittarius.screen.task.Callback;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DefaultDownloader implements Downloader {

    @Override
    public void load(Seed seed, Callback callback) throws IOException {

        HtmlContext context = new HtmlContext(seed.getUrl(),seed.getSite());
        context = load(context,seed.getSite().getTimeout());
        if(StringUtil.isBlank(context.getBody())) {
            throw new IOException("response body is null");
        }
        seed.setContext(context);
        seed.setBody(context.getBody());
        seed.setBaseUrl(context.getBaseUrl());
        if(null != callback) {
            callback.callback(seed);
        }
    }

    private String listString(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
            builder.append(s).append(";");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    @Override
    public HtmlContext load(HtmlContext post,long timeout) throws IOException {
        Connection connection = Jsoup.connect(post.getUrl()).timeout(Long.valueOf(timeout).intValue());
        Map<String,List<String>> header = post.getHeadMap();
        if(header != null) {
            for (Map.Entry<String, List<String>> item : header.entrySet()) {
                connection.header(item.getKey(),listString(item.getValue()));
            }
        }
        Map<String,String> cookies = post.getCookies();
        if(cookies != null) {
            for (Map.Entry<String, String> cookie : cookies.entrySet()) {
                connection.cookie(cookie.getKey(),cookie.getValue());
            }
        }
        Connection.Response response = connection.execute();
        post.setBody(response.body());
        post.setCode(response.statusCode());
        post.setBaseUrl(response.parse().baseUri());
        return post;
    }

}
