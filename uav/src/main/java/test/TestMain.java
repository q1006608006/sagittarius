package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by IVAN on 2017/12/10.
 */
public class TestMain {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"classpath*:spring/application-provider.xml"});
        applicationContext.start();
        System.in.read();
    }
}
