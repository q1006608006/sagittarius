package top.ivan.sagittarius.screen.spring;

import top.ivan.sagittarius.screen.context.SeedContext;

import java.io.IOException;
import java.util.List;

public interface SeedContextSupporter {
    List<SeedContext> getContexts() throws Exception;
}
