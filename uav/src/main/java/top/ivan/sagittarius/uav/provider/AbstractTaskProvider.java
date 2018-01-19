package top.ivan.sagittarius.uav.provider;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.ivan.sagittarius.uav.impl.service.TaskControllerManager;
import top.ivan.sagittarius.uav.vo.TaskMessage;

public abstract class AbstractTaskProvider implements TaskProvider,InitializingBean {

    @Autowired
    RedisTemplate<String,TaskMessage> redisTemplate;

    @Autowired
    TaskControllerManager taskControllerManager;

    String topicId;
    String cacheTopicId;
    long timeout;

    public AbstractTaskProvider(Class<? extends TaskProvider> provider) {
        TaskPath t = provider.getAnnotation(TaskPath.class);
        if(null != t && t.value().trim().length() > 0) {
            topicId = t.value();
            cacheTopicId = t.cache().replace("$",topicId);
            timeout = t.recycle();
        } else {
            topicId = provider.getCanonicalName();
            cacheTopicId = topicId + "_cache";
            timeout = 1000 * 60 * 5;
        }
    }


    @Override
    public String getTopicId() {
        return topicId;
    }

    @Override
    public String getCacheTopicId() {
        return cacheTopicId;
    }

    public final void afterPropertiesSet() {
        taskControllerManager.registerProvider(this);
    }

    public long getTimeout() {
        return timeout;
    }

}
