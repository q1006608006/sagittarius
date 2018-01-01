package top.ivan.sagittarius.uav.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.service.TaskController;
import top.ivan.sagittarius.uav.vo.TaskMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@TaskPath("sdlkj")
public class TaskControllerImpl extends AbstractTaskController {

/*    @Autowired
    private RedisTemplate<String,TaskMessage> redisTemplate;*/

    public TaskControllerImpl() {
        super(TaskControllerImpl.class);
    }

    @Override
    public TaskMessage takeTask() {
        return null;
    }

    @Override
    public boolean completeTask(TaskMessage task) {
        return false;
    }

    @Override
    public boolean putTask(TaskMessage task) {
        return false;
    }


    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContextAware = new ClassPathXmlApplicationContext("classpath:spring/application-redis.xml","classpath:spring/application-base.xml");
        TaskControllerImpl t = (TaskControllerImpl) applicationContextAware.getBean("taskControllerImpl");
//        System.out.println(t.redisTemplate.opsForSet().pop("test").getMessage());
        t.redisTemplate.opsForSet().add("test",new TaskMessage("testMessage"));
    }

}
