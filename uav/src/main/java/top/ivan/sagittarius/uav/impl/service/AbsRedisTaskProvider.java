package top.ivan.sagittarius.uav.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.vo.TaskMessage;

public abstract class AbsRedisTaskProvider extends AbstractTaskProvider {

    @Autowired
    private RedisTemplate<String,TaskMessage> redisTemplate;

    public AbsRedisTaskProvider(Class<? extends TaskProvider> provider) {
        super(provider);
    }

    @Override
    public TaskMessage getTask() {
        TaskMessage task = redisTemplate.opsForList().rightPop(getTopicId());
        return task;
    }

    @Override
    public boolean putTask(TaskMessage task) {
        return redisTemplate.opsForList().leftPush(getTopicId(),task) > 0;
    }

}
