package tech.jianshuo.model.user;

import com.jianshuo.core.constant.UserStatus;
import com.jianshuo.core.model.BaseModel;
import lombok.*;
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
