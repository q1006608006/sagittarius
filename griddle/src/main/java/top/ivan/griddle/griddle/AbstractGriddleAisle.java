package top.ivan.griddle.griddle;

import top.ivan.griddle.FocusManager;

import java.util.LinkedList;
import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public abstract class AbstractGriddleAisle implements GriddleAisle {
    private List<FocusWallet> wallets;
    private String peek;
    private String defaultValue;

    public AbstractGriddleAisle(AisleBean aisle, FocusManager manager) {
        wallets = new LinkedList<>();
        this.defaultValue = aisle.getDefaultValue();
        this.peek =aisle.getPeek();
        AisleBean.FocusInfo info = aisle.getFocus();
        while(info != null) {
            wallets.add(new FocusWallet(info,manager));
            info = info.getInterceptor();
        }
    }

    @Override
    public List<FocusWallet> getFocusWallets() {
        return wallets;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String getPeek() {
        return peek;
    }
}
