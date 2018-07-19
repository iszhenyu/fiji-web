package tech.jianshuo.fiji.biz.service;

import tech.jianshuo.fiji.biz.dto.RegisterDo;
import tech.jianshuo.fiji.biz.model.user.User;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
public interface UserService {

    User findUser(String principal);

    User registerUser(RegisterDo registerDo);

}
