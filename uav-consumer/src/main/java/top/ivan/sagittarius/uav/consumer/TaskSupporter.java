package top.ivan.sagittarius.uav.consumer;

import top.ivan.sagittarius.uav.vo.TaskMessage;

public interface TaskSupporter {

    TaskMessage takeTask();

    boolean completeTask(TaskMessage task);
}
