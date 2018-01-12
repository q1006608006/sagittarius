package top.ivan.sagittarius.uav.cache;

public interface CacheKeyOperator<T,K> {
    K primaryKey(T t);
}
