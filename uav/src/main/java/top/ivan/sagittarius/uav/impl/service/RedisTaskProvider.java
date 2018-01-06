package top.ivan.sagittarius.uav.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.vo.TaskMessage;

@Component
@TaskPath("redisTaskProvider")
public class RedisTaskProvider extends AbstractTaskProvider {

    @Autowired
    private RedisTemplate<String,TaskMessage> redisTemplate;

    public RedisTaskProvider() {
        super(RedisTaskProvider.class);
    }

    @Override
    public TaskMessage getTask() {
        TaskMessage task = redisTemplate.opsForList().rightPop(getTopicId());
        redisTemplate.opsForHash().put(getCacheTopicId(),task.getTaskId(),task);
        return task;
    }

    @Override
    public boolean putTask(TaskMessage task) {
        return redisTemplate.opsForList().leftPush(getTopicId(),task) > 0;
    }

    @Override
    public boolean completeTask(TaskMessage task) {
        return redisTemplate.opsForHash().delete(getCacheTopicId(),task.getTaskId()) > 0;
    }
}
