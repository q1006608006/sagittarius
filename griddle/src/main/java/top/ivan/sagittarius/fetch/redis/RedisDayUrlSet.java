package top.ivan.sagittarius.fetch.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisDayUrlSet {
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    private LocalDate now;

    private String getCurrentKey(String key) {
        return LocalDate.now().toString() + "_" + key;
    }

    private void check(String key) {
        LocalDate cur = LocalDate.now();
        if (null == now || !cur.isEqual(now)) {
            synchronized (this) {
                now = cur;
                redisTemplate.expire(key, 1, TimeUnit.DAYS);
            }
        }
    }

    public boolean add(String key, String value) {
        key = getCurrentKey(key);
        long l = redisTemplate.opsForSet().add(key, value);
        check(key);
        return l > 0;
    }

    public boolean isMember(String key, String value) {
        key = getCurrentKey(key);
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public long remove(String key, String... values) {
        key = getCurrentKey(key);
        return redisTemplate.opsForSet().remove(key, values);
    }

}
