package top.ivan.griddle.griddle;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public interface GriddleAisle {
    List<FocusWallet> getFocusWallets();
    String pass(String src, Map<String, String> peeMap) throws Exception;
    String getDefaultValue();
    String getPeek();
}
