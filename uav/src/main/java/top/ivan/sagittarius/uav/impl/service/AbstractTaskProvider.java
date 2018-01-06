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
    String cacheTopicId;

    public AbstractTaskProvider(Class<? extends TaskProvider> provider) {
        TaskPath t = provider.getAnnotation(TaskPath.class);
        if(null != t && t.value().trim().length() > 0) {
            topicId = t.value();
            cacheTopicId = t.cache().replace("$",topicId);
        } else {
            topicId = provider.getCanonicalName();
            cacheTopicId = topicId + "_cache";
        }
        taskControllerManager.registerProvider(this);
    }


    @Override
    public String getTopicId() {
        return topicId;
    }

    @Override
    public String getCacheTopicId() {
        return cacheTopicId;
    }

}
