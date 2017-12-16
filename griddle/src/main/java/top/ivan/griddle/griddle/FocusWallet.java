package top.ivan.griddle.griddle;

import top.ivan.griddle.Focus;
import top.ivan.griddle.FocusManager;

public class FocusWallet {
    private Focus focus;
    private String target;
    private String key;
    private String defaultValue;

    public FocusWallet(){
    }

    public FocusWallet(AisleBean.FocusInfo info, FocusManager manager) {
        this.focus = manager.getFocus(info.getType());
        this.target = info.getTarget();
        this.key = info.getKey();
    }

    public Focus getFocus() {
        return focus;
    }

    public void setFocus(Focus focus) {
        this.focus = focus;
    }

    public String getTarget() {
            return target;
        }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

}