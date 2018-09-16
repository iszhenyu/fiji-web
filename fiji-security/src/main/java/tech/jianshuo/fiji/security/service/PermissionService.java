package tech.jianshuo.fiji.security.service;

import java.util.Map;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
public interface PermissionService {

    /**
     * 初始化权限
     */
    Map<String, String> loadAllFilterChainDefinitions();

}
