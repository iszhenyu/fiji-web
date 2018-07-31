package tech.jianshuo.fiji.security.cache;

import org.apache.shiro.ShiroException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhen.yu
 * Created on 2018-07-29
 */
public class SpringRedisCacheManager implements CacheManager, Initializable, Destroyable {
    private static final Logger logger = LoggerFactory.getLogger(SpringRedisCacheManager.class);

    private org.springframework.cache.CacheManager springCacheManager;

    public org.springframework.cache.CacheManager getSpringCacheManager() {
        return springCacheManager;
    }

    public void setSpringCacheManager(org.springframework.cache.CacheManager springCacheManager) {
        this.springCacheManager = springCacheManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if (logger.isTraceEnabled()) {
            logger.trace("Acquiring SpringRedisCache instance named [" + name + "]");
        }

        try {
            org.springframework.cache.Cache cache = springCacheManager.getCache(name);
            if (logger.isInfoEnabled()) {
                logger.info("Using existing SpringRedisCache named [" + name + "]");
            }
            return new SpringRedisCache<>(cache);
        } catch (net.sf.ehcache.CacheException e) {
            throw new CacheException(e);
        }
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void init() throws ShiroException {

    }

}
