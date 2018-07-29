package tech.jianshuo.fiji.security.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 * @author zhen.yu
 * Created on 2018-07-29
 */
public class SpringCacheManagerWrapper implements CacheManager {

    private org.springframework.cache.CacheManager springCacheManager;

    public void setSpringCacheManager(org.springframework.cache.CacheManager springCacheManager) {
        this.springCacheManager = springCacheManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        org.springframework.cache.Cache cache = springCacheManager.getCache(name);
        return new SpringCacheWrapper(cache);
    }

    static class SpringCacheWrapper<K, V> implements Cache<K, V> {

        private org.springframework.cache.Cache springCache;

        SpringCacheWrapper(org.springframework.cache.Cache springCache) {
            this.springCache = springCache;
        }

        @Override
        public V get(K key) throws CacheException {
            Object object = springCache.get(key);
            if (object instanceof SimpleValueWrapper) {
                return (V) ((SimpleValueWrapper) object).get();
            }
            return (V)object;
        }

        @Override
        public V put(K key, V value) throws CacheException {
            springCache.put(key, value);
            return value;
        }

        @Override
        public V remove(K key) throws CacheException {
            springCache.evict(key);
            return null;
        }

        @Override
        public void clear() throws CacheException {
            springCache.clear();
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
}
