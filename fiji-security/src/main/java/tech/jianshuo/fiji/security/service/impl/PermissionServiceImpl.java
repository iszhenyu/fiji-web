package tech.jianshuo.fiji.security.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.biz.model.admin.AdminResource;
import tech.jianshuo.fiji.biz.service.ResourceService;
import tech.jianshuo.fiji.common.util.CollectionUtils;
import tech.jianshuo.fiji.security.service.PermissionService;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private ResourceService resourceService;

    @Override
    public Map<String, String> loadAllFilterChainDefinitions() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/error/**", "anon");
        filterChainDefinitionMap.put("/user/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/auth/register", "anon");
        filterChainDefinitionMap.put("/auth/logout", "logout");
        filterChainDefinitionMap.put("/admin/rest/auth/login", "anon");
        filterChainDefinitionMap.put("/admin/rest/auth/register", "anon");
        filterChainDefinitionMap.put("/admin/rest/auth/logout", "logout");

        List<AdminResource> adminResources = resourceService.loadUrlAndPermissions();
        if (CollectionUtils.isNotEmpty(adminResources)) {
            adminResources.stream()
                    .filter(r -> StringUtils.isNotBlank(r.getUrl()) && StringUtils.isNotBlank(r.getPermission()))
                    .forEach(resource -> {
                        String permission = "perms[" + resource.getPermission() + "]";
                        filterChainDefinitionMap.put(resource.getUrl(), permission);
                    });
        }

        //配置记住我过滤器或认证通过可以访问的地址(当上次登录时，记住我以后，在下次访问/或/index时，可以直接访问，不需要登陆)
        filterChainDefinitionMap.put("/index", "user");
        filterChainDefinitionMap.put("/", "user");
        filterChainDefinitionMap.put("/**", "authc");

        return filterChainDefinitionMap;
    }

}
