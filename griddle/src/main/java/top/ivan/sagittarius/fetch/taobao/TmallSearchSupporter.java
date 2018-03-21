package top.ivan.sagittarius.fetch.taobao;

import org.springframework.beans.factory.annotation.Autowired;
import top.ivan.sagittarius.screen.context.JsonSeedContext;
import top.ivan.sagittarius.screen.context.PartsManager;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.spring.ScreenListener;
import top.ivan.sagittarius.screen.spring.SeedContextSupporter;
import top.ivan.sagittarius.screen.utils.FileUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TmallSearchSupporter implements SeedContextSupporter {
    @Autowired
    private PartsManager partsManager;
    @Override
    public List<SeedContext> getContexts() throws IOException {
        try {
            SeedContext context = new JsonSeedContext(FileUtil.loadFile("config/taobao.json"),partsManager);
            return Arrays.asList(context);
/*            SeedContext tbContext = new JsonSeedContext(FileUtil.loadFile("config/taobao.json"), partsManager);
            SeedContext jdContext = new JsonSeedContext(FileUtil.loadFile("config/jd.json"), partsManager);
            return Arrays.asList(tbContext, jdContext);*/
//            SeedContext context = new JsonSeedContext(FileUtil.loadFile("config/jd.json"),partsManager);
//            return Arrays.asList(context);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PartsManager getPartsManager() {
        return partsManager;
    }

    public void setPartsManager(PartsManager partsManager) {
        this.partsManager = partsManager;
    }
}
