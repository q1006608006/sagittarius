import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ivan.sagittarius.uav.impl.provider.RedisTaskProviderII;
import top.ivan.sagittarius.uav.impl.service.TaskControllerManager;
import top.ivan.sagittarius.uav.vo.TaskMessage;

import java.io.IOException;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/application-base.xml","classpath:spring/application-redis.xml"})
@ContextConfiguration(locations = {"classpath:spring/application-*.xml"})
public class TestTaskPath {

    @Autowired
    RedisTaskProviderII redisTaskProviderII;

    @Autowired
    TaskControllerManager taskControllerManager;

    @Test
    public void test1() {
        System.out.println(taskControllerManager.cacheOperator);
//        redisTaskProviderII.putTask(new TaskMessage("hahaheiheihoho"));
/*        taskControllerManager.putTask("topicDDL",new TaskMessage("abcde"));
        TaskMessage message = taskControllerManager.takeTask("topicDDL");
        System.out.println(message.getMessage());
        System.out.println(taskControllerManager.completeTask("topicDDL",message));
        System.out.println(message.getClass());*/
    }


    @Test
    public void test2() throws IOException {
        System.out.println(taskControllerManager.putTask("topicDDL", new TaskMessage("abcde")));
        System.in.read();
    }

}
