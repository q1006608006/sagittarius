package top.ivan.griddle.focus;

import top.ivan.griddle.ExportFocusHandle;
import top.ivan.griddle.Focus;
import top.ivan.griddle.FocusManager;
import top.ivan.griddle.UnSupportFocusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/23
 */
public class ListFocus implements Focus, ExportFocusHandle {
    private FocusManager manager;

    /**
     * @param src
     * @param target
     * @param key
     * @return
     * @throws Exception
     *
     * @targetType {focus}[{realTarget}] or parseNull
     * @keyType {realKey} to inner focus's key
     * @parseNull $:""->null, #:null->"", +:null->"null", -:null->skip(if target start with '@'(ex:@+),then "" will be translate to null)
     */
    @Override
    public String peek(String src, String target, String key) throws Exception {
        List list;
        src = src.trim();
        if(!src.startsWith("[") && !src.endsWith("]")) {
            list = Arrays.asList(src.split(","));
        } else {
            list = JsonFocus.fromJson(src, List.class);
        }
        if (ExportFocusHandle.isExportTarget(target)) {
            Focus focus = ExportFocusHandle.getExportFocus(target,manager);
            target = ExportFocusHandle.getExportTarget(target);
            return JsonFocus.toJson(parseExport(list, focus, target, key));
        } else {
            return JsonFocus.toJson(parseNull(list, target));
        }
    }

    private List parseExport(List src, Focus mod, String target, String key) throws Exception {
        List result = new ArrayList();
        foreach(src, o -> {
            result.add(mod.peek(JsonFocus.toJson(o), target, key));
        });
        return result;
    }


    private List parseNull(List src, String type) throws Exception {
        List result = new ArrayList();
        boolean openNullValue = false;
        if (type.startsWith("@")) {
            type = type.replace("@", "");
            openNullValue = true;
        }
        boolean finalOpenNullValue = openNullValue;
        String finalType = type;
        foreach(src, o -> {
            o = getTestNullValue(o, finalOpenNullValue);
            switch (finalType) {
                case "$"://"" -> null
                    result.add(o);
                    break;
                case "#"://null -> ""
                    result.add(TestFocus.notNullValue(o));
                    break;
                case "+"://null -> "null" >> ""->"null"
                    result.add(null == o ? "null" : o);
                    break;
                case "-"://null -> skip  >> "" -> skip
                    if(null != o) {
                        result.add(o);
                    }
                    break;
                default:
                    throw new UnSupportFocusException("not suitable order found");
            }
        });
        return result;
    }

    private String getTestNullValue(Object obj, boolean openNullValue) {
        if(openNullValue) {
            return TestFocus.nullValue(obj);
        }
        return null == obj ? null : obj.toString();
    }


    public static void foreach(Object[] objs, KeyInvoker invoker) throws Exception {
        for (Object o : objs) {
            invoker.invoke(o);
        }
    }

    public static void foreach(List list, KeyInvoker invoker) throws Exception {
        foreach(list.toArray(), invoker);
    }

    @Override
    public void setManager(FocusManager manager) {
        this.manager = manager;
    }

    interface KeyInvoker {
        void invoke(Object obj) throws Exception;
    }

}
