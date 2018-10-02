package tech.jianshuo.fiji.admin.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.jianshuo.fiji.admin.service.AdminUserService;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.security.provider.SecurityUser;
import tech.jianshuo.fiji.security.provider.UserProvider;

/**
 * @author zhenyu
 * @date 2018-10-02
 */
@Slf4j
@Component
public class AdminUserProvider implements UserProvider {

    @Autowired
    private AdminUserService adminUserService;

    @Override
    public SecurityUser provideUser(String principal) {
        AdminUser user = adminUserService.loadUserByPrincipal(principal);
        if (user == null) {
            return null;
        }
        SecurityUser result = new SecurityUser();
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setSalt(user.getSalt());
        return result;
    }
}
