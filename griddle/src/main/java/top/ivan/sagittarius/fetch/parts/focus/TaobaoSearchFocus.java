package top.ivan.sagittarius.fetch.parts.focus;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.fetch.taobao.TaoBaoBeanCheckUtils;
import top.ivan.sagittarius.screen.parse.focus.Focus;
import top.ivan.sagittarius.screen.parse.focus.JsonFocus;

import java.util.Arrays;
import java.util.List;

@Component("tbs")
public class TaobaoSearchFocus implements Focus {
    @Override
    public String peek(String src, String target, String key) throws Exception {
        if(key != null) {
            return parseKey(src,key);
        }
        List<String> list = Arrays.asList(src.split("\n"));
        for (String line : list) {
            line = line.trim();
            if(line.startsWith(target)) {
                int end = line.lastIndexOf("}");
                int start = line.indexOf("{");
                return line.substring(start,end+1);
            }
        }
        return null;
    }

    private String parseKey(String url,String key) {
        String value = key.replaceAll(".+\\((.*)\\)","$1");
        String operator = key.replaceAll("\\(.*\\)","");
        if("nextUrl".equals(operator)) {
            return TaoBaoBeanCheckUtils.nextUrl(url,Integer.valueOf(value));
        } else {
            throw new RuntimeException("unknow operatop: " + operator);
        }
    }




    public static void main(String[] args) throws Exception {
        TaobaoSearchFocus focus = new TaobaoSearchFocus();
        String str = Jsoup.connect("https://s.taobao.com/search?q=打印机").get().select("script").toString();
        System.out.println();
        JsonFocus json = new JsonFocus();
        System.out.println(json.peek(focus.peek(str, "g_page_config", null), "mods.grid.data.spus", null));
    }
}
