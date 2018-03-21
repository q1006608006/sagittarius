package top.ivan.sagittarius.uav.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TaskMessage extends Message {
    private static final long serialVersionUID = 5102795445612994178L;
    private boolean complete;
    private String message;
    private String taskId;

    private Map<String,? extends Serializable> taskResult;

    public TaskMessage(String message) {
        this.message = message;
        taskId = UUID.randomUUID().toString();
    }

    public TaskMessage(TaskMessage task) {
        this(task,false);
    }

    public TaskMessage(TaskMessage task,boolean autoId) {
        this.complete = task.complete;
        this.message = task.message;
        if(autoId) {
            taskId = UUID.randomUUID().toString();
        } else {
            taskId = task.getTaskId();
        }
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
