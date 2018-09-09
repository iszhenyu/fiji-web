package tech.jianshuo.fiji.biz.service;

import java.util.List;

import tech.jianshuo.fiji.biz.model.user.Role;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
public interface RoleService {

    List<Role> loadRolesByUserId(Long userId);

}
