package top.ivan.sagittarius.screen;

import top.ivan.sagittarius.screen.download.Downloader;
import top.ivan.sagittarius.screen.download.HtmlContext;
import top.ivan.sagittarius.screen.parse.Griddle;

public class Screen {
    public void cross(Seed seed) throws Exception {
/*        Downloader downloader = seed.getDownloader();
        HtmlContext context = new HtmlContext(seed.getUrl(),seed.getSite());
        context = downloader.load(context);
        Griddle griddle = seed.getGriddle();
        seed.setStorage(griddle.doFilter(context.getBody(),seed.getSite().getStorage()));
        seed.getSpread().spread(seed);
        seed.getPersist().process(seed);*/
    }
}
