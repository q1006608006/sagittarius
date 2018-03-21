package top.ivan.sagittarius.screen.parse.focus;

import top.ivan.sagittarius.screen.parse.UnSupportFocusException;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/25
 */
public interface ExportFocusHandle {
    void setManager(FocusManager manager);

    String EXPORT_MODULE = "^([\\s\\S]*?)\\[([\\s\\S]*)\\]$";

    static boolean isExportTarget(String target) {
        return target.matches(EXPORT_MODULE);
    }

    static Focus getExportFocus(String target, FocusManager manager) throws UnSupportFocusException {
        if(null == manager) {
            throw new UnSupportFocusException("not registered manager");
        }
        String focusName = target.replaceAll(EXPORT_MODULE,"$1");
        Focus focus = manager.getFocus(focusName);
        if(null == focus) {
            throw new UnSupportFocusException(String.format("not focus with name:\"%s\" was found",focusName));
        }
        return focus;
    }

    static String getExportTarget(String srcTarget) {
        return srcTarget.replaceAll(EXPORT_MODULE,"$2");
    }
}
