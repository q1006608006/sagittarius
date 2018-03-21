package top.ivan.sagittarius.screen.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import top.ivan.sagittarius.screen.context.JsonSeedContext;
import top.ivan.sagittarius.screen.context.PartsManager;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.task.Garden;
import top.ivan.sagittarius.screen.utils.FileUtil;

import java.io.IOException;
import java.util.Arrays;

public class ScreenListener implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Garden garden;

    private PartsManager partsManager;
    private SeedContextSupporter supporter;

    public ScreenListener() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void start() throws Exception {
        garden = new Garden(partsManager);
        for (SeedContext context : supporter.getContexts()) {
            garden.hold(context);
        }
        garden.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> garden.shutdown()));
    }

    public void setSupporter(SeedContextSupporter supporter) {
        this.supporter = supporter;
    }

    public PartsManager getPartsManager() {
        return partsManager;
    }

    public void setPartsManager(PartsManager partsManager) {
        this.partsManager = partsManager;
    }
}
