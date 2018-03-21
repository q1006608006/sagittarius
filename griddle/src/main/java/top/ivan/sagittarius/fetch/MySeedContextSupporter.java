package top.ivan.sagittarius.fetch;

import org.springframework.beans.factory.annotation.Autowired;
import top.ivan.sagittarius.screen.context.JsonSeedContext;
import top.ivan.sagittarius.screen.context.PartsManager;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.spring.SeedContextSupporter;
import top.ivan.sagittarius.screen.utils.FileUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MySeedContextSupporter implements SeedContextSupporter {

    @Autowired
    private PartsManager partsManager;

    @Override
    public List<SeedContext> getContexts() throws Exception {
        SeedContext tbContext = new JsonSeedContext(FileUtil.loadFile("config/taobao.json"), partsManager);
        SeedContext jdContext = new JsonSeedContext(FileUtil.loadFile("config/jd.json"), partsManager);
        return Arrays.asList(tbContext, jdContext);
    }
}
