package top.ivan.sagittarius.screen.spring;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.gson.annotations.SerializedName;
import top.ivan.sagittarius.screen.parse.focus.JsonFocus;
import top.ivan.sagittarius.screen.utils.FileUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PartsBean {
    private List<PartBean> focus;
    private List<PartBean> downloader;
    private List<PartBean> spread;
    private List<PartBean> persist;
    private List<ContextBean> context;

    public List<ContextBean> getContext() {
        return context;
    }

    public void setContext(List<ContextBean> context) {
        this.context = context;
    }

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

    public static class ContextBean {
        private String path;
        private String activeTime;
        private Long at;
        private boolean isLoad;
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public long getActiveTime() {
            if(at == null) {
                if (StringUtils.isBlank(activeTime)) {
                    at = System.currentTimeMillis();
                }
                String[] times = activeTime.split(" ");
                LocalDateTime dateTime = LocalDateTime.now();
                if(times.length == 2) {
                    dateTime = LocalDateTime.parse(activeTime,formatter);
                } else if(times[0].contains("-")) {
                    dateTime = LocalDateTime.of(LocalDate.parse(times[0]), LocalTime.now());
                } else if (times[0].contains(":")) {
                    dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(times[0]));
                } else {
                    throw new RuntimeException(String.format("can not parse '%s' as LocalDateTime",activeTime));
                }
                at = dateTime.toEpochSecond(ZoneOffset.of("+8")) * 1000;
            }
            return at;
        }

        public static void main(String[] args) throws IOException {
            PartsBean p  = JsonFocus.fromJson(FileUtil.loadFile("config/focus.json"),PartsBean.class);
            for (ContextBean bean : p.context) {
                System.out.println(bean.getActiveTime());
                System.out.println(bean.getPath());
                System.out.println(bean.isLoad());
            }
/*            String str = "2017-12-01";
            ContextBean bean = new ContextBean();
            bean.setActiveTime(str);
            System.out.println(bean.getActiveTime());*/
        }

        public void setActiveTime(String activeTime) {
            this.activeTime = activeTime;
        }

        public boolean isLoad() {
            return isLoad;
        }

        public void setLoad(boolean load) {
            isLoad = load;
        }
    }
}
