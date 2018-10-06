package tech.jianshuo.fiji.biz.model.admin;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.jianshuo.fiji.biz.model.Model;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "fj_admin_user_role")
public class AdminUserRole extends Model {
    private static final long serialVersionUID = -1302438435305903555L;

    private Long userId;
    private Long roleId;

}
