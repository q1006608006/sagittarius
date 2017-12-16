package top.ivan.griddle.annotation;

import java.lang.annotation.*;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Peek {
    String test();
}
