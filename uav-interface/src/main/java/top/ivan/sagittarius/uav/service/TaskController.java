package top.ivan.sagittarius.uav.service;

import top.ivan.sagittarius.uav.vo.TaskMessage;

public interface TaskController {

    TaskMessage takeTask(String topicId);

    boolean completeTask(String topicId,TaskMessage task);

    boolean putTask(String topicId,TaskMessage task);

}
