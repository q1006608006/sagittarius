package top.ivan.sagittarius.screen.spring;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PartsBean {
    private List<PartBean> focus;
    private List<PartBean> downloader;
    private List<PartBean> spread;
    private List<PartBean> persist;

    public List<PartBean> getFocus() {
        return focus;
    }

    public void setFocus(List<PartBean> focus) {
        this.focus = focus;
    }

    public List<PartBean> getDownloader() {
        return downloader;
    }

    public void setDownloader(List<PartBean> downloader) {
        this.downloader = downloader;
    }

    public List<PartBean> getSpread() {
        return spread;
    }

    public void setSpread(List<PartBean> spread) {
        this.spread = spread;
    }

    public List<PartBean> getPersist() {
        return persist;
    }

    public void setPersist(List<PartBean> persist) {
        this.persist = persist;
    }

    public static class PartBean {
        String type;
        String alias;
        @SerializedName("class")
        String className;
        String beanId;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getBeanId() {
            return beanId;
        }

        public void setBeanId(String beanId) {
            this.beanId = beanId;
        }
    }
}
