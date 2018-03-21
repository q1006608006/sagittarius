package top.ivan.sagittarius.uav.impl.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.ivan.sagittarius.uav.provider.AbstractTaskProvider;
import top.ivan.sagittarius.uav.provider.TaskProvider;
import top.ivan.sagittarius.uav.vo.TaskMessage;

import javax.annotation.Resource;

public abstract class AbsRedisTaskProvider extends AbstractTaskProvider {

    @Resource(name = "redisTemplate")
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

    public void setRedisTemplate(RedisTemplate<String,TaskMessage> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
