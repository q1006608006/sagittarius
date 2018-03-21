package top.ivan.sagittarius.fetch.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisTimeCache {
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    private long startMillions;

    public long getStartMillions() {
        return startMillions;
    }

    public void setStartMillions(long startMillions) {
        this.startMillions = startMillions;
    }


    public static void main(String[] args) {

    }
}
