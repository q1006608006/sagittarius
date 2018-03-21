package top.ivan.sagittarius.screen.parse;

import top.ivan.sagittarius.screen.parse.focus.FocusWallet;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public interface GriddleAisle extends Serializable {
    List<FocusWallet> getFocusWallets();

    String pass(String src, Map<String, String> peeMap) throws UnParsedException;

    String getDefaultValue();

    String getPeek();
}
