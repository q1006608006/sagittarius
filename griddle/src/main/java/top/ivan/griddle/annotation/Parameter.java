package top.ivan.griddle.annotation;

import java.lang.annotation.*;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Parameter {
    String value();
}
