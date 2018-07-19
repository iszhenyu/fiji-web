package tech.jianshuo.model.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tech.jianshuo.core.constant.UserStatus;
import tech.jianshuo.core.model.BaseModel;

/**
 * @author zhen.yu
 * @since 2018/7/1
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {
    private static final long serialVersionUID = -5487198824559449323L;

    private String username;
    private String password;
    private String salt;
    private String email;
    private String mobile;

    private String avatarUrl;
    private String introduction;
    private UserStatus status;

}
