package top.ivan.griddle.utils;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public class ClassBuilder {
    public static <T> T build(Class<T> clazz) throws Exception {
        return clazz.newInstance();
    }

}
