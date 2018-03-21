package top.ivan.sagittarius.screen.parse;

import top.ivan.sagittarius.screen.Seed;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public interface Griddle extends Serializable {
    Map<String, String> doFilter(Seed seed);
}
