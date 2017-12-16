package top.ivan.griddle.test;

import top.ivan.griddle.Griddle;
import top.ivan.griddle.IXHtml;
import top.ivan.griddle.core.FocusManagerBuilder;
import top.ivan.griddle.core.GriddleBuilder;
import top.ivan.griddle.loader.HtmlLoaderImpl;

import java.io.File;
import java.util.HashMap;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/21
 */
public class testLoader {

    public static void main(String[] args) throws Exception {
        HtmlLoaderImpl loader = new HtmlLoaderImpl();
        IXHtml html = loader.loaderHtml("https://detail.tmall.com/item.htm?id=559791372740");
        Griddle grddle = GriddleBuilder.builder().manager(FocusManagerBuilder.config().def().build()).file(new File("config/test.json"),"filters").build();
        HashMap<String,String> map = new HashMap<>();
        map.put("id","559791372740");
        String value = grddle.doFilter(html.getBody(),map).get("result");
        System.out.println(value);
//        List lit = JsonFocus.fromJson(value,new TypeToken<List>(){}.getType());
//        lit.forEach(o->{
//            System.out.println(o);
//        });
//        lit.forEach(System.out::println);
//        System.out.println();
//        System.out.println(Arrays.asList("1","2"));

//        System.out.println(LocalDate.now().withMonth(1).toString().replace("-","").substring(0,4));
    }

}
