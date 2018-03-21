package top.ivan.sagittarius.fetch.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisSetOperator {
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean put(String id,String url) {
        return redisTemplate.opsForSet().add(id,url) > 0;
    }

    public long add(String id,Object object) {
        return redisTemplate.opsForList().leftPush(id,object);
    }

    public Object pop(String id) {
        return redisTemplate.opsForList().rightPop(id);
    }
}
