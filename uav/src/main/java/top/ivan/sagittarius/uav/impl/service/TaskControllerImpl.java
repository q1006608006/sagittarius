package top.ivan.sagittarius.uav.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import top.ivan.sagittarius.uav.service.TaskController;
import top.ivan.sagittarius.uav.vo.TaskMessage;

import java.util.Arrays;

@TaskPath("sdlkj")
public class TaskControllerImpl extends AbstractTaskController {

    public TaskControllerImpl() {
        super(TaskControllerImpl.class);
    }

    @Override
    public TaskMessage takeTask() {
        return null;
    }

    @Override
    public boolean completeTask(TaskMessage task) {
        return false;
    }

    @Override
    public boolean putTask(TaskMessage task) {
        return false;
    }


    public static void main(String[] args) {
        TaskControllerImpl t = new TaskControllerImpl();
    }
}
