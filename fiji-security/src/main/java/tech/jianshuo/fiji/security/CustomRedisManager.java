package tech.jianshuo.fiji.security;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.crazycake.shiro.RedisManager;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhen.yu
 * Created on 2018-07-24
 */
public class CustomRedisManager extends RedisManager {
    private static JedisPool jedisPool = null;

    private String host = "127.0.0.1";
    private int port = 6379;
    private int expire = 2592000;
    private int timeout = 2000;
    private int database = 0;
    private String password = null;

    public CustomRedisManager() {
    }

    @Override
    public void init() {
        this.password = StringUtils.isEmpty(this.password) ? null : this.password;
        if (jedisPool == null) {
            jedisPool = new JedisPool(new JedisPoolConfig(), this.host, this.port, this.timeout, this.password, database);
        }
    }

    @Override
    public byte[] get(byte[] key) {
        byte[] value;
        Jedis jedis = jedisPool.getResource();

        try {
            value = jedis.get(key);
        } finally {
            jedis.close();
        }

        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            jedis.close();
        }

        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            jedis.close();
        }

        return value;
    }

    @Override
    public void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }

    }

    @Override
    public void flushDB() {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.flushDB();
        } finally {
            jedis.close();
        }

    }

    @Override
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();

        try {
            dbSize = jedis.dbSize();
        } finally {
            jedis.close();
        }

        return dbSize;
    }

    @Override
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();

        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            jedis.close();
        }

        return keys;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public int getExpire() {
        return this.expire;
    }

    @Override
    public void setExpire(int expire) {
        this.expire = expire;
    }

    @Override
    public int getTimeout() {
        return this.timeout;
    }

    @Override
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        if (null == database) {
            return;
        }
        this.database = database;
    }
}
