package top.ivan.sagittarius.screen.parse;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/30
 */
public class UnSupportFocusException extends Exception {
    public UnSupportFocusException(String str) {
        super(str);
    }

    public UnSupportFocusException(Throwable e) {
        super(e);
    }

    public UnSupportFocusException(String msg, Throwable e) {
        super(msg, e);
    }
}
