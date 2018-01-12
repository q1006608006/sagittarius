package top.ivan.sagittarius.uav.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.cache.CacheOperator;
import top.ivan.sagittarius.uav.service.TaskController;
import top.ivan.sagittarius.uav.vo.TaskMessage;

import java.util.HashMap;
import java.util.Map;

@Component
public class TaskControllerManager implements TaskController {

    @Autowired
    private CacheOperator<String, TaskMessageEx> cacheOperator;

    protected final boolean registerProvider(TaskProvider provider) {
        if (taskProviderMap.containsKey(provider.getTopicId())) {
            return false;
        }
        this.taskProviderMap.put(provider.getTopicId(), provider);
        return true;
    }

    private Map<String, TaskProvider> taskProviderMap = new HashMap<>();

    @Override
    public TaskMessage takeTask(String topicId) {
        validate(topicId);
        TaskProvider provider = taskProviderMap.get(topicId);
        TaskMessage task = provider.getTask();
        handleTask(task,provider);
        return task;
    }

    @Override
    public boolean completeTask(String topicId, TaskMessage task) {
        validate(topicId);
        TaskProvider provider = taskProviderMap.get(topicId);
        removeCache(task,provider);
        return provider.completeTask(task);
    }

    @Override
    public boolean putTask(String topicId, TaskMessage task) {
        validate(topicId);
        return taskProviderMap.get(topicId).putTask(task);
    }

    private void validate(String topicId) {
        if (!taskProviderMap.containsKey(topicId)) {
            throw new NullPointerException("not such topic:\"" + topicId + "\" had be found");
        }
    }


    public TaskMessageEx handleTask(TaskMessage task,TaskProvider provider) {
        if (!(task instanceof TaskMessageEx)) {
            task = new TaskMessageEx(task);
        }
        cacheOperator.cache(provider.getCacheTopicId(), task.getTaskId(), (TaskMessageEx) task);
        return (TaskMessageEx) task;
    }

    public TaskMessageEx removeCache(TaskMessage task,TaskProvider provider) {
        return cacheOperator.removeCache(provider.getCacheTopicId(),task.getTaskId());
    }


    public static class TaskMessageEx extends TaskMessage {
        public TaskMessageEx(TaskMessage task) {
            super(task, false);
            this.taskBirth = System.currentTimeMillis();
        }

        private long taskBirth;

        public long getTaskBirth() {
            return taskBirth;
        }

        public void setTaskBirth(long taskBirth) {
            this.taskBirth = taskBirth;
        }
    }
}
