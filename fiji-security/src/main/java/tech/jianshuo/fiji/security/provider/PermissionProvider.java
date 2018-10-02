package tech.jianshuo.fiji.security.provider;

import java.util.Map;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
public interface PermissionProvider {

    /**
     * url: permission
     * @return 权限列表
     */
    Map<String, String> providePermissions();
}
