package top.ivan.griddle;

import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/19
 */
public interface Riddle {
    Map<String,Object> doFilter(IXHtml html);
}
