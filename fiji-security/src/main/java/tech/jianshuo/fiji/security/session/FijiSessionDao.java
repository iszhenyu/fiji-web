package tech.jianshuo.fiji.security.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import tech.jianshuo.fiji.core.exception.ValidationException;

/**
 * @author zhen.yu
 * Created on 2018-07-31
 *
 * 这里主要是重写SimpleSession的序列化，以json的形式存储到redis中
 */
public class FijiSessionDao extends AbstractSessionDAO implements CacheManagerAware {

    private static final String ACTIVE_SESSION_CACHE_NAME = "fiji-activeSessionCache";

    private CacheManager cacheManager;
    private SessionSerializer sessionSerializer;
    private Cache<Serializable, String> activeSessions;

    private String activeSessionsCacheName = ACTIVE_SESSION_CACHE_NAME;

    public FijiSessionDao() {
        sessionSerializer = new FijiSessionSerializer();
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    @Override
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public SessionSerializer getSessionSerializer() {
        return sessionSerializer;
    }

    public void setSessionSerializer(SessionSerializer sessionSerializer) {
        this.sessionSerializer = sessionSerializer;
    }

    public String getActiveSessionsCacheName() {
        return activeSessionsCacheName;
    }

    public void setActiveSessionsCacheName(String activeSessionsCacheName) {
        this.activeSessionsCacheName = activeSessionsCacheName;
    }

    private Cache<Serializable, String> getActiveSessionsCacheLazy() {
        if (this.activeSessions == null) {
            this.activeSessions = createActiveSessionsCache();
        }
        return activeSessions;
    }

    private Cache<Serializable, String> createActiveSessionsCache() {
        Cache<Serializable, String> cache = null;
        if (cacheManager != null) {
            String name = getActiveSessionsCacheName();
            cache = cacheManager.getCache(name);
        }
        return cache;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        return sessionId;
    }

    /**
     * 这里只使用redis保存session，所以doReadSession直接返回null即可
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        return null;
    }

    public Serializable create(Session session) {
        Serializable sessionId = super.create(session);
        cache(session, sessionId);
        return sessionId;
    }

    protected Session getCachedSession(Serializable sessionId) {
        Session cached = null;
        if (sessionId != null) {
            Cache<Serializable, String> cache = getActiveSessionsCacheLazy();
            if (cache != null) {
                cached = getCachedSession(sessionId, cache);
            }
        }
        return cached;
    }

    private Session getCachedSession(Serializable sessionId, Cache<Serializable, String> cache) {
        SessionSerializer serializer = getSessionSerializer();
        if (serializer == null) {
            throw new ValidationException("SessionSerializer is null!!!");
        }
        return serializer.deserialize(cache.get(sessionId));
    }

    private void cache(Session session, Serializable sessionId) {
        if (session == null || sessionId == null) {
            return;
        }
        Cache<Serializable, String> cache = getActiveSessionsCacheLazy();
        if (cache == null) {
            return;
        }
        cache(session, sessionId, cache);
    }

    private void cache(Session session, Serializable sessionId, Cache<Serializable, String> cache) {
        SessionSerializer serializer = getSessionSerializer();
        if (serializer == null) {
            throw new ValidationException("SessionSerializer is null!!!");
        }
        cache.put(sessionId, serializer.serialize(session));
    }

    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        Session s = getCachedSession(sessionId);
        if (s == null) {
            s = super.readSession(sessionId);
        }
        return s;
    }

    public void update(Session session) throws UnknownSessionException {
        if (session instanceof ValidatingSession) {
            if (((ValidatingSession) session).isValid()) {
                cache(session, session.getId());
            } else {
                uncache(session);
            }
        } else {
            cache(session, session.getId());
        }
    }

    public void delete(Session session) {
        uncache(session);
    }

    private void uncache(Session session) {
        if (session == null) {
            return;
        }
        Serializable id = session.getId();
        if (id == null) {
            return;
        }
        Cache<Serializable, String> cache = getActiveSessionsCacheLazy();
        if (cache != null) {
            cache.remove(id);
        }
    }

    public Collection<Session> getActiveSessions() {
        Cache<Serializable, String> cache = getActiveSessionsCacheLazy();
        if (cache != null) {
            SessionSerializer serializer = getSessionSerializer();
            if (serializer == null) {
                throw new ValidationException("SessionSerializer is null.");
            }
            return cache.values().stream()
                    .map(serializer::deserialize)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptySet();
        }
    }

}
