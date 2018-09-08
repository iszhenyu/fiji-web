package tech.jianshuo.fiji.security.session;

import org.apache.shiro.session.Session;

/**
 * @author zhen.yu
 * Created on 2018-09-08
 */
public interface SessionSerializer {

    String serialize(Session session);

    Session deserialize(String sessionStr);
}
