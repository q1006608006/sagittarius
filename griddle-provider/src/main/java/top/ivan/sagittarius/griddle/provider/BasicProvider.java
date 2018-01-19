package top.ivan.sagittarius.griddle.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.impl.provider.AbsRedisTaskProvider;
import top.ivan.sagittarius.uav.impl.service.TaskControllerManager;
import top.ivan.sagittarius.uav.provider.TaskProvider;
import top.ivan.sagittarius.uav.vo.TaskMessage;

public abstract class BasicProvider extends AbsRedisTaskProvider {

    public BasicProvider(Class<? extends TaskProvider> provider) {
        super(provider);
    }

}
