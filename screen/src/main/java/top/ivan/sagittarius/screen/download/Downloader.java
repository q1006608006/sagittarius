package top.ivan.sagittarius.screen.download;

import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.task.Callback;

import java.io.IOException;
import java.util.Map;

public interface Downloader {
    Seed load(Seed seed) throws IOException;
    HtmlContext load(HtmlContext post,long timeout) throws IOException,InterruptedException;
}
