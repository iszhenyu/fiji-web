package tech.jianshuo.fiji.admin.vo;

import lombok.Data;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;

import java.io.Serializable;

/**
 * @author zhen.yu
 * Created on 2018-09-06
 */
@Data
public class LoginVo {
    private Serializable token;
    private Long userId;
    private String username;
    private String avatarUrl;
    private String introduction;

    public static LoginVo fromAdminUserAndToken(AdminUser user, Serializable token) {
        LoginVo vo = new LoginVo();
        vo.setToken(token);
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setAvatarUrl(user.getAvatarUrl());
        vo.setIntroduction(user.getIntroduction());
        return vo;
    }
}
