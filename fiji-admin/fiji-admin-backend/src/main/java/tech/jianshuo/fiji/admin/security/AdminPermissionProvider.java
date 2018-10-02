package tech.jianshuo.fiji.admin.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.jianshuo.fiji.security.provider.PermissionProvider;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhenyu
 * @date 2018-10-02
 */
@Slf4j
@Component
public class AdminPermissionProvider implements PermissionProvider {

    @Override
    public Map<String, String> providePermissions() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/error/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/admin/rest/auth/login", "anon");
        filterChainDefinitionMap.put("/admin/rest/auth/register", "anon");
        filterChainDefinitionMap.put("/admin/rest/auth/logout", "logout");

//        List<AdminResource> adminResources = resourceService.loadUrlAndPermissions();
//        if (CollectionUtils.isNotEmpty(adminResources)) {
//            adminResources.stream()
//                    .filter(r -> StringUtils.isNotBlank(r.getUrl()) && StringUtils.isNotBlank(r.getPermission()))
//                    .forEach(resource -> {
//                        String permission = "perms[" + resource.getPermission() + "]";
//                        filterChainDefinitionMap.put(resource.getUrl(), permission);
//                    });
//        }

        // 配置记住我过滤器或认证通过可以访问的地址(当上次登录时，记住我以后，在下次访问/或/index时，可以直接访问，不需要登陆)
        filterChainDefinitionMap.put("/index", "user");
        filterChainDefinitionMap.put("/", "user");
        filterChainDefinitionMap.put("/**", "authc");

        return filterChainDefinitionMap;
    }
}
