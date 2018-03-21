package top.ivan.sagittarius.screen.parse.focus;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/12/1
 */
public class MapFocus implements Focus {
    private static final long serialVersionUID = 2270568219705993429L;

    @Override
    public String peek(String src, String target, String key) throws Exception {
        Map<String, String> map = JsonFocus.fromJson(key, new TypeToken<Map<String, String>>() {
        }.getType());
        if (isSetKey(target)) {
            List<String> keyList = getKeyList(target);
            boolean remove = isIgnore(target);
            ignoreItems(map, keyList, remove);
        }
        map.put(getPutKey(target), src);
        return JsonFocus.toJson(map);
    }

    private static void ignoreItems(Map<String, String> src, List<String> keys, boolean remove) {
        List<String> removeKeys = keys;
        if (!remove) {
            removeKeys = new ArrayList<>(src.keySet());
            removeKeys.removeAll(keys);
        }
        for (String key : removeKeys) {
            src.remove(key);
        }
    }

    private static List<String> getKeyList(String target) {
        String str = target.replaceAll(".*\\[!?(.+)\\]", "$1");
        return Arrays.asList(str.split(","));
    }

    private static boolean isIgnore(String target) {
        return target.matches(".*\\[!.*\\]");
    }

    private static boolean isSetKey(String target) {
        return target.matches(".*\\[.+\\]");
    }

    private static String getPutKey(String target) {
        if (isSetKey(target)) {
            return target.replaceAll("\\[!?.*\\]", "");
        }
        return target;
    }
}
