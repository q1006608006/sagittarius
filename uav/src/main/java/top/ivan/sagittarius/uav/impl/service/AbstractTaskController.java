package top.ivan.sagittarius.uav.impl.service;

import top.ivan.sagittarius.uav.service.TaskController;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;

public abstract class AbstractTaskController implements TaskController {
    public AbstractTaskController(Class<? extends TaskController> controller) {
        TaskPath t = controller.getAnnotation(TaskPath.class);
        System.out.println(t);
        if(null != t) {
            System.out.println(t.value());
        }
    }
}
