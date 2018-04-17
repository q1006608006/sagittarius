package top.ivan.sagittarius.screen.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import top.ivan.sagittarius.screen.context.JsonSeedContext;
import top.ivan.sagittarius.screen.context.PartsManager;
import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.defalutparts.DefaultFocusManager;
import top.ivan.sagittarius.screen.download.Downloader;
import top.ivan.sagittarius.screen.operator.persist.PersistOperator;
import top.ivan.sagittarius.screen.operator.spread.SpreadOperator;
import top.ivan.sagittarius.screen.parse.focus.Focus;
import top.ivan.sagittarius.screen.parse.focus.FocusManager;
import top.ivan.sagittarius.screen.parse.focus.JsonFocus;
import top.ivan.sagittarius.screen.utils.FileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpringPartsManager implements PartsManager,InitializingBean,ApplicationContextAware {
    private ApplicationContext context;
    private PartsBean partsBean;
    private FocusManager focusManager;
    private Map<String,Downloader> downloaderMapping;
    private Map<String,SpreadOperator> spreadMapping;
    private Map<String,PersistOperator> persistMapping;

    private String configPath;

    public SpringPartsManager(ApplicationContext context) {
        this.context = context;
    }

    public SpringPartsManager(){}

    public void load(String json) throws Exception {
        this.partsBean = JsonFocus.fromJson(json,PartsBean.class);
        loadDownloader();
        loadFocus();
        loadPersist();
        loadSpread();
    }

    private <T> T getInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (T) Class.forName(className).newInstance();
    }

    private void loadFocus () throws Exception {
        focusManager = new DefaultFocusManager();
        for (PartsBean.PartBean partBean : partsBean.getFocus()) {
            if(null != partBean.getBeanId()) {
                focusManager.setFocus(partBean.getType(), (Focus) context.getBean(partBean.getBeanId()));
            } else if(null != partBean.getClassName()){
                focusManager.setFocus(partBean.getType(), getInstance(partBean.getClassName()));
            } else {
                throw new Exception("can not load focus: " + partBean.getType());
            }
        }
    }

    private <T> void loadPartMap(Map<String, T> map, List<PartsBean.PartBean> beans) throws Exception {
        for (PartsBean.PartBean bean : beans) {
            if(bean.getBeanId() != null) {
                map.put(bean.getAlias(), (T) context.getBean(bean.getBeanId()));
            } else if(null != bean.getClassName()){
                map.put(bean.getAlias(),getInstance(bean.getClassName()));
            } else {
                throw new Exception("can not load operator: " + bean.getAlias());
            }
        }
    }

    private void loadSpread() throws Exception {
        spreadMapping = new HashMap<>();
        if(partsBean.getSpread() != null) {
            loadPartMap(spreadMapping, partsBean.getSpread());
        }
    }

    private void loadPersist() throws Exception {
        persistMapping = new HashMap<>();
        if(partsBean.getPersist() != null) {
            loadPartMap(persistMapping, partsBean.getPersist());
        }
    }

    private void loadDownloader() throws Exception {
        downloaderMapping = new HashMap<>();
        loadPartMap(downloaderMapping,partsBean.getDownloader());
    }

    @Override
    public Downloader getDownLoader(String alias) {
        return downloaderMapping.get(alias);
    }

    @Override
    public PersistOperator getPersistOperator(String alias) {
        return persistMapping.get(alias);
    }

    @Override
    public FocusManager getFocusManager() {
        return focusManager;
    }

    @Override
    public SpreadOperator getSpreadOperator(String alias) {
        return spreadMapping.get(alias);
    }

    @Override
    public List<SeedContext> getContexts() {
        return partsBean.getContext().stream()
                .filter(PartsBean.ContextBean::isLoad)
                .map(context-> {
                    try {
                        SeedContext seedContext = new JsonSeedContext(FileUtil.loadFile(context.getPath()), this);
                        ((JsonSeedContext) seedContext).setActiveTime(context.getActiveTime());
                        return seedContext;
                    } catch (Exception e) {
                        throw new RuntimeException("can not load file: " + context.getPath());
                    }
                }).collect(Collectors.toList());
    }

    public static void main(String[] args) throws Exception {
        SpringPartsManager manager = new SpringPartsManager(null);
        manager.load(FileUtil.loadFile("config/focus.json"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        load(FileUtil.loadFile(configPath));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }
}
