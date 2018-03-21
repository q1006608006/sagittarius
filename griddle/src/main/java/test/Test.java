package test;

import top.ivan.sagittarius.screen.context.JsonSeedContext;
import top.ivan.sagittarius.screen.context.PartsManager;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.spring.ScreenListener;
import top.ivan.sagittarius.screen.spring.SeedContextSupporter;
import top.ivan.sagittarius.screen.spring.SpringPartsManager;
import top.ivan.sagittarius.screen.utils.FileUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Test {
    public static class TestContextSupporter implements SeedContextSupporter {
        public PartsManager partsManager;
        @Override
        public List<SeedContext> getContexts() throws IOException {
            try {
                return Collections.singletonList(
                        new JsonSeedContext(FileUtil.loadFile("config/jd.json"), partsManager));
            } catch (Exception e) {
                return null;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        SpringPartsManager partsManager = new SpringPartsManager();
        partsManager.load(FileUtil.loadFile("config/focus.json"));
        TestContextSupporter supporter = new TestContextSupporter();
        supporter.partsManager = partsManager;
        ScreenListener listener = new ScreenListener();
        listener.setPartsManager(partsManager);
        listener.setSupporter(supporter);
        listener.start();
    }
}
