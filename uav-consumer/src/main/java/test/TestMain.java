package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ivan.sagittarius.uav.service.MessageController;
import top.ivan.sagittarius.uav.service.TestService;
import top.ivan.sagittarius.uav.vo.Message;
import top.ivan.sagittarius.uav.vo.TaskMessage;

import java.util.HashMap;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        TestService testService;
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-consume.xml");

        long st = System.currentTimeMillis();

        testService = (TestService) context.getBean("testApiImpl");
        System.out.println(testService.sayHello("ivan"));

        long et = System.currentTimeMillis();
        System.out.println("cost time: " + (st - et));
        st = et;

        MessageController controller = (MessageController) context.getBean("messageController");
        Message a = controller.takeMessage("testQueue");
        System.out.println(a);
        Map<String,String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        System.out.println(controller.putMessage("testQueue",new Message(map)));
        a = controller.takeMessage("testQueue");
        System.out.println(a.takeInfo("a"));
        System.out.println(a.takeInfo("b"));

        et = System.currentTimeMillis();
        System.out.println("cost time: " + (st - et));
        st = et;

        Thread.sleep(1000);
        System.out.println(controller.putMessage("testQueue",new Message(map)));
        a = controller.takeMessage("testQueue");
        System.out.println(a.takeInfo("a"));
        System.out.println(a.takeInfo("b"));

        et = System.currentTimeMillis();
        System.out.println("cost time: " + (st - et));
        st = et;

        Thread.sleep(1000);
        System.out.println(controller.putMessage("testQueue",new Message(map)));
        a = controller.takeMessage("testQueue");
        System.out.println(a.takeInfo("a"));
        System.out.println(a.takeInfo("b"));

        et = System.currentTimeMillis();
        System.out.println("cost time: " + (st - et));
        st = et;

        Thread.sleep(1000);
        System.out.println(controller.putMessage("testQueue",new Message(map)));
        a = controller.takeMessage("testQueue");
        System.out.println(a.takeInfo("a"));
        System.out.println(a.takeInfo("b"));

        et = System.currentTimeMillis();
        System.out.println("cost time: " + (st - et));
        st = et;

    }
}
