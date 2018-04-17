package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.Site;
import top.ivan.sagittarius.screen.download.Downloader;
import top.ivan.sagittarius.screen.download.HtmlContext;
import top.ivan.sagittarius.uav.consumer.TaskSupporterManager;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws InterruptedException, IOException {
/*        TestService testService;
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-consume.xml");
        TaskController taskController = (TaskController) context.getBean("taskController");
        System.out.println(taskController.putTask("topicDDL", new TaskMessage("tesst")));
        System.out.println(System.in.read());
        TaskMessage message = taskController.takeTask("topicDDL");
        System.out.println(System.in.read());
        System.out.println(taskController.completeTask("topicDDL", message));*/
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-consume.xml");

        Downloader downloader = (Downloader) context.getBean("cloudDownloader");
        System.out.println(downloader);
        Seed seed = new Seed();
        seed.setUrl("https://www.baidu.com");
        seed.setSite(new Site());
        System.out.println(downloader.load(seed).getBody());
/*        System.out.println("c1=======================");
        System.out.println(context1.getBody());
        System.out.println("c2======================");
        System.out.println(c2.getBody());*/
/*
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
*/

    }
}
