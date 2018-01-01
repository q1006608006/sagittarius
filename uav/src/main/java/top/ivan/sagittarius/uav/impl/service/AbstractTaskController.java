package top.ivan.sagittarius.uav.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.service.TaskController;
import top.ivan.sagittarius.uav.vo.TaskMessage;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;

public abstract class AbstractTaskController implements TaskController {
    @Autowired
    RedisTemplate<String,TaskMessage> redisTemplate;
    public AbstractTaskController(Class<? extends TaskController> controller) {
        TaskPath t = controller.getAnnotation(TaskPath.class);
        System.out.println(t);
        if(null != t) {
            System.out.println(t.value());
        }
    }
}
