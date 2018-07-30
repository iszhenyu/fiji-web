package tech.jianshuo.fiji.security.service;

import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**
 * @author zhen.yu
 * Created on 2018-07-29
 */
public interface PasswordService {

    String encryptPassword(String planTextPassword, ByteSource salt);

    default String encryptPassword(String planTextPassword, String salt) {
        SimpleByteSource sourceSalt = new SimpleByteSource(salt);
        return encryptPassword(planTextPassword, sourceSalt);
    }

    String generateSalt();
}
