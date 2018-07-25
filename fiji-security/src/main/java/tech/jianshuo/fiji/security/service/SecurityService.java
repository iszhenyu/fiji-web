package tech.jianshuo.fiji.security.service;

import tech.jianshuo.fiji.biz.model.user.User;

/**
 * @author zhen.yu
 * Created on 2018-07-26
 */
public interface SecurityService {
    User registerUser(String principal, String credential);
}
