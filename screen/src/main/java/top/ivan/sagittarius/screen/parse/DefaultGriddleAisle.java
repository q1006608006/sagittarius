package top.ivan.sagittarius.screen.parse;

import top.ivan.sagittarius.screen.parse.focus.FocusManager;
import top.ivan.sagittarius.screen.parse.focus.FocusWallet;
import top.ivan.sagittarius.screen.parse.focus.ReplaceFocus;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public class DefaultGriddleAisle implements GriddleAisle {

    private static final long serialVersionUID = -5362670198867933087L;
    private List<FocusWallet> wallets;
    private String peek;
    private String defaultValue;
    private FocusManager manager;

    public DefaultGriddleAisle(AisleBean aisle, FocusManager manager) {
        this.manager = manager;
        wallets = new LinkedList<>();
        this.defaultValue = aisle.getDefaultValue();
        this.peek = aisle.getPeek();
        AisleBean.FocusInfo info = aisle.getFocus();
        while (info != null) {
            wallets.add(new FocusWallet(info));
            info = info.getInterceptor();
        }
    }

    @Override
    public List<FocusWallet> getFocusWallets() {
        return wallets;
    }

    @Override
    public String pass(String src, Map<String, String> peeMap) throws UnParsedException {
        List<FocusWallet> focusWallets = getFocusWallets();
        String target, key, ret = src;
        FocusWallet wallet;
        for (int i = 0; i < focusWallets.size(); i++) {
            wallet = focusWallets.get(i);
            target = wallet.getTarget();
            key = wallet.getKey();
            key = ReplaceFocus.peeKey(key, peeMap);
            try {
                ret = manager.getFocus(wallet.getType()).peek(ret, target, key);
            } catch (Exception e) {
                if (wallet.getDefaultValue() != null) {
                    ret = wallet.getDefaultValue();
                } else {
                    throw new UnParsedException(focusWallets,i,e,target,key);
                }
            }
        }
        return ret;
    }


    @Override
    public String getPeek() {
        return peek;
    }

    public void setPeek(String peek) {
        this.peek = peek;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
