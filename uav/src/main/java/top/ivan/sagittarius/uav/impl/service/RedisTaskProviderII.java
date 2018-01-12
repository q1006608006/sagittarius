package top.ivan.sagittarius.uav.impl.service;


import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.vo.TaskMessage;

@Component
@TaskPath("topicDDL")
public class RedisTaskProviderII extends AbsRedisTaskProvider {
    public RedisTaskProviderII() {
        super(RedisTaskProviderII.class);
    }

    public void print() {
        System.out.println(getTopicId());
        System.out.println(getCacheTopicId());
    }

    @Override
    public boolean completeTask(TaskMessage task) {
//        System.out.println(task.getMessage());
        return false;
    }
}
