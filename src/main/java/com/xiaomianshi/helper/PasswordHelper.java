package com.xiaomianshi.helper;

import com.xiaomianshi.core.Const;
import com.xiaomianshi.util.CipherUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhen.yu
 * @since 2018/7/12
 */
public class PasswordHelper {
    private static final Logger logger = LoggerFactory.getLogger(PasswordHelper.class);

    public static String encrypt(String password, String salt) {
        try {
            String hashed =  CipherUtils.hmacSHA256Digest(Const.PASSWORD_KEY,password + salt);
            logger.debug("password: {}, salt: {}, hashed: {}", password, salt, hashed);
            return hashed;
        } catch (Exception e) {
            logger.error("密码加密异常：", e);
        }
        return null;
    }

}
