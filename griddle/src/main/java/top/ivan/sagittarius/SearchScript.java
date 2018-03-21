package top.ivan.sagittarius;

import org.jsoup.Jsoup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ivan.sagittarius.griddle.persist.dao.ProductPreviewDao;
import top.ivan.sagittarius.screen.spring.ScreenListener;

public class SearchScript {
    public static void main(String[] args) throws Exception {
        ScreenListener listener;
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-*.xml", "classpath*:spring/application-*.xml");
        listener = (ScreenListener) context.getBean("screenListener");
        System.out.println(listener + " " + System.currentTimeMillis());
        listener.start();
//        ProductPreviewDao productPreviewDao = (ProductPreviewDao) context.getBean("productPreviewDao");
//        System.out.println(productPreviewDao.all(0,10));

    }
}
