package top.ivan.sagittarius.cloud;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.TestMain;

import java.io.IOException;

public class CloudConfig {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"classpath*:spring/application-*.xml"});
        System.out.println(applicationContext.getBean("taskControllerManager"));
        applicationContext.start();
        System.in.read();
    }
}
