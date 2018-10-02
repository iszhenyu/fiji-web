package tech.jianshuo.fiji.security.session;

import org.apache.shiro.session.Session;

/**
 * @author zhen.yu
 * Created on 2018-09-08
 */
public interface SessionSerializer {

    /**
     * 将Session对象序列化为字符串
     * @param session Session 实现
     * @return 序列化后的字符串
     */
    String serialize(Session session);

    /**
     * 将字符串反序列化为Session实例
     * @param sessionStr 字符串
     * @return Session实例
     */
    Session deserialize(String sessionStr);
}
