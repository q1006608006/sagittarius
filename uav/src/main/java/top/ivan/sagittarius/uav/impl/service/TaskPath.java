package top.ivan.sagittarius.uav.impl.service;


import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskPath {
    String value();
    String cache() default "$_cache";
    long recycle() default 1000 * 60 * 5;
}
