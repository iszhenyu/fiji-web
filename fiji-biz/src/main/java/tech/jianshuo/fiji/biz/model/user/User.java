package tech.jianshuo.fiji.biz.model.user;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.jianshuo.fiji.biz.constant.UserStatus;
import tech.jianshuo.fiji.core.model.BaseModel;

/**
 * @author zhen.yu
 * @since 2018/7/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "fj_user")
public class User extends BaseModel {

    private String username;
    private String password;
    private String salt;
    private String email;
    private String mobile;

    private String avatarUrl;
    private String introduction;
    private UserStatus status;

}
