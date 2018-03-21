package top.ivan.sagittarius.fetch.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class RedisBlockList {
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    public long add(String key,Object value) {
        return redisTemplate.opsForList().leftPush(key,value);
    }

    public Object get(String key) {
        return redisTemplate.opsForList().rightPop(key,0, TimeUnit.SECONDS);
    }

}
