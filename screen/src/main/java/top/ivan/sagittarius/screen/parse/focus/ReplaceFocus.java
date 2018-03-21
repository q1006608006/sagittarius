package top.ivan.sagittarius.screen.parse.focus;

import top.ivan.sagittarius.screen.parse.UnSupportFocusException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public class ReplaceFocus implements Focus {

    private static final long serialVersionUID = -4558343262539187134L;

    /**
     * replace with regex
     *
     * @param src
     * @param target replace source
     * @param key    replacement
     * @return
     * @example src: "hello world",target: ".*(l+)",key: "$1ive" ==> "lived"
     */
    @Override
    public String peek(String src, String target, String key) throws Exception {
/*        if (!src.matches("[\\s\\S]*" + target + "[\\s\\S]*")) {
            throw new UnSupportFocusException("can not match values:'" + getErrorSource(src) + "' with regex: '" + target + "'");
        }*/
        return src.replaceAll(target, key);
    }

    private String getErrorSource(String src) {
        int length = src.length();
        if(length > 20) {
            src = src.substring(0,10) + "..." + src.substring(length - 11);
        }
        return src;
    }


    private static final Pattern $_pattern = Pattern.compile("\\{\\$([\\s\\S]*?)\\}");

    public static String peeKey(String key, Map<String, String> tempMap) {
        if (key == null) {
            return null;
        }
        if (key.equalsIgnoreCase("{$$}")) {
            return JsonFocus.toJson(tempMap);
        }
        Matcher matcher = $_pattern.matcher(key);
        String repart, rekey, temp;
        while (matcher.find()) {
            repart = matcher.group(0);
            rekey = matcher.group(1);
            temp = tempMap.get(rekey) + "";
            key = key.replace(repart, temp);
        }
        return key;
    }

}
