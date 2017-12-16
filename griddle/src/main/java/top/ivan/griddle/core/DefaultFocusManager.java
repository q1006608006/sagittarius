package top.ivan.griddle.core;

import top.ivan.griddle.ExportFocusHandle;
import top.ivan.griddle.Focus;
import top.ivan.griddle.FocusManager;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/31
 */
public class DefaultFocusManager implements FocusManager {
    private Map<String,Focus> focusMap = new HashMap<>();
    @Override
    public Focus getFocus(String type) {
        return focusMap.get(type);
    }

    public void setFocus(String type,Focus focus) {
        focusMap.put(type,focus);
        if(focus instanceof ExportFocusHandle) {
            ((ExportFocusHandle) focus).setManager(this);
        }
    }

    public Focus removeFocus(String type) {
        return focusMap.remove(type);
    }
}
