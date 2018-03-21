package top.ivan.sagittarius.uav.consumer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ivan.sagittarius.uav.service.TaskController;

public class TaskSupporterManager implements ApplicationContextAware {
    private static TaskController taskController;
    private static ApplicationContext context;

    public static void init(String[] springConfigPath) {
        context = new ClassPathXmlApplicationContext(springConfigPath);
        taskController = (TaskController) context.getBean("taskController");
    }

    private static void checkInit() {
        if (taskController == null) {
            if (context == null) {
                throw new RuntimeException("not init spring config");
            } else {
                taskController = (TaskController) context.getBean("taskController");
            }
        }
    }

    public static TaskSupporter getTaskSupporter(String topicId) {
        checkInit();
        return new DefaultTaskSupporter(topicId,taskController);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public void setTaskController(TaskController controller) {
        taskController = controller;
    }
}
