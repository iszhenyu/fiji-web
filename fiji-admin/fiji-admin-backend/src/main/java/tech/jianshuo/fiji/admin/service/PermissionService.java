package tech.jianshuo.fiji.admin.service;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
public interface PermissionService {

    /**
     * 重新加载权限
     */
    void refreshPermissions();

    /**
     * 重新加载用户权限
     */
    void refreshPermissionsOfUser(Long userId);

    /**
     * 重新加载所有拥有roleId角色的用户的权限
     */
    void refreshPermissionsOfRole(Long roleId);

}
