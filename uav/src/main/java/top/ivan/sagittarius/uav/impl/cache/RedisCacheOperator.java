package top.ivan.sagittarius.uav.impl.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.ivan.sagittarius.uav.cache.CacheKeyOperator;
import top.ivan.sagittarius.uav.cache.CacheOperator;

@Component
public class RedisCacheOperator<K,V> implements CacheOperator<K,V> {

    @Autowired
    private RedisTemplate<String,V> redisTemplate;

    @Override
    public K cache(String space, CacheKeyOperator<V, K> primary, V v) {
        K key = primary.primaryKey(v);
        redisTemplate.opsForHash().put(space,key,v);
        return key;
    }

    @Override
    public K cache(String space, K key, V v) {
        redisTemplate.opsForHash().put(space,key,v);
        return key;
    }

    @Override
    public V removeCache(String space, K key) {
        return null;
    }
}
