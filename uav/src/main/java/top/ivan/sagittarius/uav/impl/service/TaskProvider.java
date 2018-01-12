package top.ivan.sagittarius.uav.impl.service;

import top.ivan.sagittarius.uav.vo.TaskMessage;

public interface TaskProvider {

    String getTopicId();

    String getCacheTopicId();

    TaskMessage getTask();

    long getTimeout();

    boolean putTask(TaskMessage task);

    boolean completeTask(TaskMessage task);

}
