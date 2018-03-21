package top.ivan.sagittarius.screen;

import top.ivan.sagittarius.screen.context.SeedContext;
import top.ivan.sagittarius.screen.download.HtmlContext;
import top.ivan.sagittarius.screen.operator.spread.SpreadWallet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Seed implements Serializable {

    private static final long serialVersionUID = -8429398485355196411L;
    private String url;
    private String baseUrl;
    private Site site;
    private String location;
    private SpreadWallet spread;
    private Map<String,String> storage;
    private Serializable baggage;
    private int generation;
    private boolean indivisible;
    private boolean isDownloadable = true;
    private String body;
    private HtmlContext context;

    public Seed() {}

    public Seed(String location,Site site,String url,SpreadWallet spread) {
        this.location = location;
        this.spread =spread;
        this.site = site;
        this.url = url;
    }

    public Seed(SeedContext context, String url, String ruleId) {
        this(context.getLocation(),context.getSite(),url,ruleId);
        this.spread.setRule(context.getRuleMap().get(ruleId));
    }

    public Seed(String location,Site site,String url,String ruleId) {
        this.location = location;
        this.site = site;
        this.url = url;
        spread = new SpreadWallet();
        spread.setRuleId(ruleId);
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, String> getStorage() {
        return storage;
    }

    public void setStorage(Map<String, String> storage) {
        this.storage = storage;
    }

    public SpreadWallet getSpread() {
        return spread;
    }

    public void setSpread(SpreadWallet spread) {
        this.spread = spread;
    }

    public Seed newSeed(String url,SpreadWallet wallet) {
        Seed seed = new Seed();
        seed.url = url;
        seed.spread = wallet;
//        seed.parent = this;
        seed.setLocation(location);
        seed.setSite(site);
        seed.setGeneration(generation + 1);
        seed.setIndivisible(false);
        seed.isDownloadable = true;
        return seed;
    }

    public Seed copy() {
        Seed seed = new Seed();
//        seed.parent = this.parent;
        seed.setLocation(location);
        seed.setSite(site);
        seed.setGeneration(generation);
        seed.setBody(body);
        seed.setSpread(spread);
        seed.setUrl(url);
        seed.setIndivisible(indivisible);
        if(null != storage) {
            seed.setStorage(new HashMap<>(storage));
        }
        return seed;
    }

    @Override
    public String toString() {
        return "Seed@" + Integer.toHexString(hashCode()) + ":" + url;
    }

    public boolean isIndivisible() {
        return indivisible;
    }

    public void setIndivisible(boolean indivisible) {
        this.indivisible = indivisible;
    }

    public Serializable getBaggage() {
        return baggage;
    }

    public void setBaggage(Serializable baggage) {
        this.baggage = baggage;
    }

    public boolean isDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        isDownloadable = downloadable;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public HtmlContext getContext() {
        return context;
    }

    public void setContext(HtmlContext context) {
        this.context = context;
    }
}
