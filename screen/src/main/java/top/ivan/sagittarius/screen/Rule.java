package top.ivan.sagittarius.screen;

import top.ivan.sagittarius.screen.operator.persist.PersistWallet;
import top.ivan.sagittarius.screen.operator.spread.SpreadWallet;
import top.ivan.sagittarius.screen.parse.Griddle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Rule implements Serializable {
    private static final long serialVersionUID = -735369970812535700L;
    private String ruleId;
    private Griddle griddle;
    private Map<String,String> field;
    private List<SpreadWallet> spreadList;
    private List<PersistWallet> persistList;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }


    public List<SpreadWallet> getSpreadList() {
        return spreadList;
    }

    public void setSpreadList(List<SpreadWallet> spreadList) {
        this.spreadList = spreadList;
    }

    public List<PersistWallet> getPersistList() {
        return persistList;
    }

    public void setPersistList(List<PersistWallet> persistList) {
        this.persistList = persistList;
    }

    public Griddle getGriddle() {
        return griddle;
    }

    public void setGriddle(Griddle griddle) {
        this.griddle = griddle;
    }

    public Map<String, String> getField() {
        return field;
    }

    public void setField(Map<String, String> field) {
        this.field = field;
    }
}
