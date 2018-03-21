package top.ivan.sagittarius.fetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class TestStart {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://search.jd.com/s_new.php?keyword=%E6%95%B0%E7%A0%81&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&scrolling=y&log_id=1521553056.48426&tpl=1_M")
                .header("Referer","https://search.jd.com/Search")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36")
                .get();

        System.out.println(doc.select("li"));

    }
}
