package top.ivan.sagittarius.screen.parse;

import top.ivan.sagittarius.screen.parse.focus.FocusWallet;

import java.util.ArrayList;
import java.util.List;

public class UnParsedException extends Exception {

    private final List<FocusWallet> wallets;
    private int errPos;
    private String key;
    private String target;

    public UnParsedException(List<FocusWallet> wallets, int errPos, Throwable error,String target,String key) {
        super(error);
        this.errPos = errPos;
        this.wallets = wallets;
        this.key = key;
        this.target = target;
    }

    public int getErrPos() {
        return errPos;
    }

    public List<FocusWallet> getFocusWallets() {
        return this.wallets;
    }

    public String getErrorChainInfo() {
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < wallets.size(); i++) {
            if (errPos == i) {
                strList.add("->" + wallets.get(i).getType() + "<-");
            } else {
                strList.add(wallets.get(i).getType());
            }
        }
        return strList.toString();
    }

    public String getErrorKey() {
        return key;
    }

    public String getErrorTarget() {
        return target;
    }
}
