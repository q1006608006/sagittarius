package top.ivan.sagittarius.screen.operator.spread;

import com.google.gson.annotations.SerializedName;
import top.ivan.sagittarius.screen.Rule;

import java.io.Serializable;

public class SpreadWallet implements Serializable {
    private static final long serialVersionUID = -2457847316928285367L;
    private String processor;
    private transient Rule rule;
    @SerializedName("rule")
    private String ruleId;
    private String downloader;
    private int maxDeep;
    private String expression;
    private String list;
    private String match;
    private long activeInterval;

    public SpreadWallet() {
    }
    public SpreadWallet(SpreadWallet wallet) {
        this.processor = wallet.processor;
        this.rule = wallet.rule;
        this.ruleId = wallet.ruleId;
        this.downloader = wallet.downloader;
        this.maxDeep = wallet.maxDeep;
        this.expression = wallet.expression;
        this.list = wallet.list;
        this.match = wallet.match;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public String getDownloader() {
        return downloader;
    }

    public void setDownloader(String downloader) {
        this.downloader = downloader;
    }

    public int getMaxDeep() {
        return maxDeep;
    }

    public void setMaxDeep(int maxDeep) {
        this.maxDeep = maxDeep;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public long getActiveInterval() {
        return activeInterval;
    }

    public void setActiveInterval(long activeInterval) {
        this.activeInterval = activeInterval;
    }
}
