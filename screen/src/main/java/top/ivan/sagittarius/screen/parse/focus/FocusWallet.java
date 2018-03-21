package top.ivan.sagittarius.screen.parse.focus;

import top.ivan.sagittarius.screen.parse.AisleBean;

import java.io.Serializable;

public class FocusWallet implements Serializable {
    private static final long serialVersionUID = -506325061284873879L;
    private String target;
    private String key;
    private String defaultValue;
    private String type;

    public FocusWallet() {}

    public FocusWallet(AisleBean.FocusInfo info) {
        this.type = info.getType();
        this.target = info.getTarget();
        this.key = info.getKey();
        this.defaultValue = info.getDefaultValue();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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