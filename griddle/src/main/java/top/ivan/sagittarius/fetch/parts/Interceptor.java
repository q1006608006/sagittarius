package top.ivan.sagittarius.fetch.parts;

import top.ivan.sagittarius.screen.Seed;

import java.util.Map;

public interface Interceptor {
    void intercept(Seed seed,Map<String,String> map);
}