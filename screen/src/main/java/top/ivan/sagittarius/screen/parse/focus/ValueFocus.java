package top.ivan.sagittarius.screen.parse.focus;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/21
 */
public class ValueFocus implements Focus {
    private static final long serialVersionUID = 8598892342376209352L;

    @Override
    public String peek(String src, String target, String key) throws Exception {
        return key == null ? target : key;
    }
}
