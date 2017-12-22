package top.ivan.sagittarius.uav.vo;

import java.util.UUID;

public class TaskMessage extends Message {
    private boolean complete;
    private String message;
    private String taskId;

    public TaskMessage(String taskId, String message) {
        this.taskId = taskId;
        this.message = message;
        complete = false;
    }

    public TaskMessage(String message) {
        this.message = message;
        taskId = UUID.randomUUID().toString();
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getMessage() {
        return message;
    }

    public String getTaskId() {
        return taskId;
    }

}
