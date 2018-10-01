package tech.jianshuo.fiji.biz.model.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.jianshuo.fiji.biz.model.Model;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminRoleResource extends Model {
    private static final long serialVersionUID = -7191052008691998075L;

    private Long roleId;
    private Long resourcesId;

}
