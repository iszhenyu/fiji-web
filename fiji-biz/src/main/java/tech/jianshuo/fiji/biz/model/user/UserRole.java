package tech.jianshuo.fiji.biz.model.user;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.jianshuo.fiji.core.model.BaseModel;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "fj_user_role")
public class UserRole extends BaseModel {

    private Long userId;
    private Long roleId;

}