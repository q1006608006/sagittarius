package top.ivan.griddle.focus;

import top.ivan.griddle.Focus;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/21
 */
public class ValueFocus implements Focus {
    @Override
    public String peek(String src, String target, String key) throws Exception {
        return key == null ? target : key;
    }
}
