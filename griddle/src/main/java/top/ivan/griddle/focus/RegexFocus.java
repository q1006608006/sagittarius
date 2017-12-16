package top.ivan.griddle.focus;

import top.ivan.griddle.Focus;
import top.ivan.griddle.UnSupportFocusException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public class RegexFocus implements Focus {

    /**
     * replace with regex
     * @param src
     * @param target replace source
     * @param key replacement
     * @return
     * @example src: "hello world",target: ".*(l+)",key: "$1ive" ==> "lived"
     */
    @Override
    public String peek(String src, String target, String key) throws Exception {
        if(!src.matches(".*"+target+".*")) {
            throw new UnSupportFocusException("can not match any");
        }
        return src.replaceAll(target, key);
    }


    private static final Pattern $_pattern = Pattern.compile("\\{\\$([\\s\\S]*?)\\}");

    public static String peeKey(String key, Map<String, String> tempMap) {
        if(key == null) {
            return null;
        }
        if(key.equalsIgnoreCase("{$$}")) {
            return JsonFocus.toJson(tempMap);
        }
        Matcher matcher = $_pattern.matcher(key);
        String repart,rekey,temp;
        while (matcher.find()) {
            repart = matcher.group(0);
            rekey = matcher.group(1);
            temp = tempMap.get(rekey) + "";
            key = key.replace(repart,temp);
        }
        return key;
    }

}
