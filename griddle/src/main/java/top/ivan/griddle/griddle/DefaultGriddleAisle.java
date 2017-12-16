package top.ivan.griddle.griddle;

import top.ivan.griddle.FocusManager;
import top.ivan.griddle.focus.RegexFocus;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public class DefaultGriddleAisle extends AbstractGriddleAisle {

    public DefaultGriddleAisle(AisleBean aisle, FocusManager manager) {
        super(aisle, manager);
    }

    @Override
    public String pass(String src, Map<String, String> peeMap) throws Exception {
        List<FocusWallet> focusWallets = getFocusWallets();
        String target,key,ret = src;
        FocusWallet wallet;
        for(int i=0;i < focusWallets.size();i ++) {
            wallet = focusWallets.get(i);
            target = wallet.getTarget();
            key = wallet.getKey();
            key = RegexFocus.peeKey(key,peeMap);
            try {
                ret = wallet.getFocus().peek(ret, target, key);
            } catch (Exception e) {
                if(wallet.getDefaultValue() != null) {
                    ret = wallet.getDefaultValue();
                } else {
                    throw e;
                }
            }
        }
        return ret;
    }

}
