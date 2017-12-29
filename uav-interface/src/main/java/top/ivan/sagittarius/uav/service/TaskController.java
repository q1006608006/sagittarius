package top.ivan.sagittarius.uav.service;

import top.ivan.sagittarius.uav.vo.TaskMessage;

public interface TaskController {

    TaskMessage takeTask();

    boolean completeTask(TaskMessage task);

    boolean putTask(TaskMessage task);

}
