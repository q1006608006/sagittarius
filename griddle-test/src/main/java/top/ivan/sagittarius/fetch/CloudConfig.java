package top.ivan.sagittarius.fetch;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CloudConfig {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath*:spring/application-*.xml"});
        applicationContext.start();

    }
}
