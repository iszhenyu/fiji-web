package tech.jianshuo.fiji.biz.model.user;

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
@Table(name = "fj_role_resource")
public class RoleResource extends Model {
    private static final long serialVersionUID = -7191052008691998075L;

    private Long roleId;
    private Long resourcesId;

}
