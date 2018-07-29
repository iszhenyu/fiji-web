package tech.jianshuo.fiji.security.service;

/**
 * @author zhen.yu
 * Created on 2018-07-29
 */
public interface PasswordService {

    String encryptPassword(String planTextPassword, String salt);

    String generateSalt();
}
