package tech.jianshuo.fiji.security.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import tech.jianshuo.component.util.RandomUtils;
import tech.jianshuo.fiji.security.SecurityConstants;
import tech.jianshuo.fiji.security.service.PasswordService;

/**
 * @author zhen.yu
 * Created on 2018-07-29
 */
@Service
public class PasswordServiceImpl implements PasswordService {

    @Override
    public String generateSalt() {
        return RandomUtils.randomString(4);
    }

    @Override
    public String encryptPassword(String planTextPassword, ByteSource salt) {
        if (StringUtils.isBlank(planTextPassword)) {
            return "";
        }
        SimpleHash hash = new SimpleHash(
                SecurityConstants.HASH_NAME,
                planTextPassword,
                salt,
                SecurityConstants.HASH_TIMES
        );
        return hash.toHex();
    }

}
