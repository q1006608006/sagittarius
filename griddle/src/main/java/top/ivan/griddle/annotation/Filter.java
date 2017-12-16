package top.ivan.griddle.annotation;

import java.lang.annotation.*;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Filter {
    String value();
}
