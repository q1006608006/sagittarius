package top.ivan.griddle.focus;

import com.google.gson.*;
import top.ivan.griddle.Focus;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public class JsonFocus implements Focus {

    /**
     * json type data select
     *
     * @param src
     * @param target data path (ex: src:{'id':20171119} target:id ==> 20171119)
     * @param key    if target is null,then path will use key value
     * @return
     */
    @Override
    public String peek(String src, String target, String key) {
        if (null == TestFocus.nullValue(target)) {
            target = key;
        }
        return takeJsonValue(src, target);
    }

    public static String takeJsonValue(String src, String path) {
        Object obj = takeObject(fromJson(src, JsonElement.class), path);
        if(obj instanceof JsonPrimitive || (obj instanceof JsonArray && ((JsonArray) obj).size() == 1)) {
            return ((JsonElement)obj).getAsString();
        }
        return toJson(obj);
    }

    public static <T> T takeFormatObject(String src, String path, Type type) {
        JsonElement o = (JsonElement) takeObject(fromJson(src, JsonElement.class), path);
        return GSON.fromJson(o, type);
    }

    private static Object takeObject(JsonElement json, String path) {
        String[] parts = path.split("\\.");
        JsonElement temp = json;
        String key;
        for (int i = 0; i < parts.length; i++) {
            key = parts[i];
            temp = anyKey(temp, key);
        }
        return temp;
    }

    private static JsonElement anyKey(JsonElement json, String key) {
        if (key.contains("[")) {
            return getArrayValue(json, key);
        }
        return json.getAsJsonObject().get(key);
    }

    private static JsonElement getArrayValue(JsonElement json, String curkey) {
        Matcher matcher = pattern.matcher(curkey);
        String key = curkey.replaceAll("\\[\\S*", "");
        JsonArray array;
        if ("".equals(key)) {
            array = json.getAsJsonArray();
        } else {
            array = json.getAsJsonObject().getAsJsonArray(key);
        }
        while (matcher.find()) {
            json = array.get(Integer.valueOf(matcher.group(1)));
            if (json instanceof JsonArray) {
                array = (JsonArray) json;
            }
        }
        return json;
    }

    private static final Pattern pattern = Pattern.compile("\\[(\\d*?)\\]");
    private static final Gson GSON = new GsonBuilder().create();

    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static String toJson(Object obj) {
        if(obj instanceof String) {
            return (String) obj;
        }
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<? extends T> clazz) {
        return GSON.fromJson(json, clazz);
    }

}
