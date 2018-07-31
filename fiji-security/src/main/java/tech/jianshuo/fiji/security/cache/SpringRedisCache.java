package tech.jianshuo.fiji.security.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhen.yu
 * Created on 2018-07-31
 */
public class SpringRedisCache<K, V> implements Cache<K, V> {

    private static final Logger logger = LoggerFactory.getLogger(SpringRedisCache.class);

    private org.springframework.cache.Cache springCache;

    public SpringRedisCache(org.springframework.cache.Cache springCache) {
        if (springCache == null) {
            throw new IllegalArgumentException("Cache argument cannot be null.");
        }
        this.springCache = springCache;
    }

    @Override
    public V get(K key) throws CacheException {
        try {
            if (logger.isTraceEnabled()) {
                logger.trace("Getting object from cache [" + springCache.getName() + "] for key [" + key + "]");
            }
            if (key == null) {
                return null;
            } else {
                org.springframework.cache.Cache.ValueWrapper valueWrapper = springCache.get(key);
                if (valueWrapper == null) {
                    if (logger.isTraceEnabled()) {
                        logger.trace("ValueWrapper for [" + key + "] is null.");
                    }
                    return null;
                } else {
                    //noinspection unchecked
                    return (V) valueWrapper.get();
                }
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        if (logger.isTraceEnabled()) {
            logger.trace("Putting object in cache [" + springCache.getName() + "] for key [" + key + "]");
        }
        try {
            V previous = get(key);
            springCache.put(key, value);
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        if (logger.isTraceEnabled()) {
            logger.trace("Removing object from cache [" + springCache.getName() + "] for key [" + key + "]");
        }
        try {
            V previous = get(key);
            springCache.evict(key);
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        if (logger.isTraceEnabled()) {
            logger.trace("Clearing all objects from cache [" + springCache.getName() + "]");
        }
        try {
            springCache.clear();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");
    }

    @Override
    public Set<K> keys() {
        throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
    }
}
