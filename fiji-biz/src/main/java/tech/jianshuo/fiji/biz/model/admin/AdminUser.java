package tech.jianshuo.fiji.biz.model.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.jianshuo.fiji.biz.constant.UserStatus;
import tech.jianshuo.fiji.biz.model.Model;

/**
 * @author zhen.yu
 * @since 2018/7/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminUser extends Model {
    private static final long serialVersionUID = 3730107904024788810L;

    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String salt;
    private String email;
    private String mobile;

    private String avatarUrl;
    private String introduction;
    private UserStatus status;

}
