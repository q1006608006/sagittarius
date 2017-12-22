package top.ivan.sagittarius.uav.service;

import top.ivan.sagittarius.uav.vo.TaskMessage;

public interface TaskController {
    TaskMessage takeTask(String queueId);
    boolean completeTask(TaskMessage task);
}
