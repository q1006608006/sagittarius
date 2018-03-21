package top.ivan.sagittarius.screen.defalutparts;

import top.ivan.sagittarius.screen.parse.focus.Focus;
import top.ivan.sagittarius.screen.parse.focus.ExportFocusHandle;
import top.ivan.sagittarius.screen.parse.focus.FocusManager;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/31
 */
public class DefaultFocusManager implements FocusManager {
    private static final long serialVersionUID = 2988381960399219821L;
    private Map<String, Focus> focusMap = new HashMap<>();

    @Override
    public Focus getFocus(String type) {
        return focusMap.get(type);
    }

    @Override
    public void setFocus(String type, Focus focus) {
        focusMap.put(type, focus);
        if (focus instanceof ExportFocusHandle) {
            ((ExportFocusHandle) focus).setManager(this);
        }
    }

    public Focus removeFocus(String type) {
        return focusMap.remove(type);
    }
}
