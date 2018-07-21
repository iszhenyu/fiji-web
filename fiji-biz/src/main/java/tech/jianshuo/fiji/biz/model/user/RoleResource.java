package tech.jianshuo.fiji.biz.model.user;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.jianshuo.fiji.core.model.BaseModel;

/**
 * @author yuzhen <yuzhen@kuaishou.com>
 * Created on 2018-07-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "fj_role_resource")
public class RoleResource extends BaseModel {

    private Long roleId;
    private Long resourcesId;

}
