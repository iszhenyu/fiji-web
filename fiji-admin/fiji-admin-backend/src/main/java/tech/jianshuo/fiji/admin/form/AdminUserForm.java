package tech.jianshuo.fiji.admin.form;

import lombok.Data;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;

import javax.validation.constraints.NotEmpty;

/**
 * @author zhenyu
 * @date 2018-10-09
 */
@Data
public class AdminUserForm {

    private Long userId;

    @NotEmpty(message = "用户名不能为空")
    private String username;

    private String password;
    private String nickname;
    private Long[] roleIds;

    public AdminUser getAdminUser() {
        AdminUser user = new AdminUser();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setId(userId);
        user.setPassword(password);
        return user;
    }
}
