package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class TestJd {
    public static void main(String[] args) throws IOException {
        for(int i = 0;i < 10;i++) {
            Document document = Jsoup.connect("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E6%89%8B%E6%9C%BA&pvid=b3484a303ddc45c78a8a2b767a973a90")
                    .get();
            System.out.println(document.outerHtml().contains("2999.00"));
        }
    }
}
