package top.ivan.sagittarius.uav.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import top.ivan.sagittarius.uav.service.TaskController;
import top.ivan.sagittarius.uav.vo.TaskMessage;

//@Component
public class DefaultTaskSupporter implements TaskSupporter {

    private String topicId;
    @Autowired
    private TaskController taskController;

    public DefaultTaskSupporter() {}

    public DefaultTaskSupporter(String topicId,TaskController controller) {
        this.topicId = topicId;
        this.taskController = controller;
    }

    @Override
    public TaskMessage takeTask() {
        return taskController.takeTask(topicId);
    }

    @Override
    public boolean completeTask(TaskMessage task) {
        return taskController.completeTask(topicId, task);
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

}
