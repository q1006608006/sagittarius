package top.ivan.sagittarius.uav.cache;

public interface CacheOperator<K,T> {
    K cache(String space,CacheKeyOperator<T,K> primary,T t);
    K cache(String space,K key,T t);
    T removeCache(String space,K key);
}
