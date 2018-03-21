package top.ivan.sagittarius.screen.parse.focus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/30
 */
public class TypeFocus implements Focus {
    private static final long serialVersionUID = -739753725606622555L;
    static Map<String, Class<?>> typeMap = new HashMap<>();

    {
        typeMap.put("int", Integer.class);
        typeMap.put("char", Character.class);
        typeMap.put("long", Long.class);
        typeMap.put("short", Short.class);
        typeMap.put("double", Double.class);
        typeMap.put("float", Float.class);
        typeMap.put("String", String.class);
        typeMap.put("byte", Byte.class);
        typeMap.put("list", List.class);
        typeMap.put("map", Map.class);
    }

    /**
     * @param src
     * @param target type of src
     * @param key    not used
     * @return Object::toString
     * @throws Exception
     */
    @Override
    public String peek(String src, String target, String key) throws Exception {
        Class<?> typeClass = typeMap.get(target);
        if (typeClass == null) {
            typeClass = Class.forName(target);
        }
        return JsonFocus.fromJson(src, typeClass).toString();
    }

}
