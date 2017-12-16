package top.ivan.griddle.griddle;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public class AisleBean {
    private String peek;
    private FocusInfo focus;
    private String defaultValue;

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getPeek() {
        return peek;
    }

    public void setPeek(String peek) {
        this.peek = peek;
    }

    public FocusInfo getFocus() {
        return focus;
    }

    public void setFocus(FocusInfo focus) {
        this.focus = focus;
    }

    public class FocusInfo {
        private String target;
        private String type;
        private String key;
        private FocusInfo interceptor;
        private String defaultValue;

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public FocusInfo getInterceptor() {
            return interceptor;
        }

        public void setInterceptor(FocusInfo interceptor) {
            this.interceptor = interceptor;
        }
    }
}
