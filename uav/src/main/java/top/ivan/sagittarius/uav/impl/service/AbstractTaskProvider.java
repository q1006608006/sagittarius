package top.ivan.sagittarius.uav.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.service.TaskController;
import top.ivan.sagittarius.uav.vo.TaskMessage;

public abstract class AbstractTaskProvider implements TaskProvider {

    @Autowired
    RedisTemplate<String,TaskMessage> redisTemplate;

    @Autowired
    TaskControllerManager taskControllerManager;

    String topicId;

    public AbstractTaskProvider(Class<? extends TaskProvider> provider) {
        TaskPath t = provider.getAnnotation(TaskPath.class);
        if(null != t && t.value().trim().length() > 0) {
            topicId = t.value();
        } else {
            topicId = provider.getCanonicalName();
        }
        taskControllerManager.registerProvider(this);
    }


    public static void main(String[] args) {
        new Thread(()-> System.out.println("adiao should pay a computer for ivan")).start();
    }
}
