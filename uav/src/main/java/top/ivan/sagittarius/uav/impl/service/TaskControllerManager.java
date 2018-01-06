package top.ivan.sagittarius.uav.impl.service;

import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.service.TaskController;
import top.ivan.sagittarius.uav.vo.TaskMessage;

import java.util.HashMap;
import java.util.Map;

@Component
public class TaskControllerManager implements TaskController {

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
        return taskProviderMap.get(topicId).getTask();
    }

    @Override
    public boolean completeTask(String topicId, TaskMessage task) {
        validate(topicId);
        return taskProviderMap.get(topicId).completeTask(task);
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

    private class ProviderContext {
        TaskProvider provider;
    }
}
