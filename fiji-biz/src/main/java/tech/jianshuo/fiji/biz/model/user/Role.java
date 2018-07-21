package tech.jianshuo.fiji.biz.model.user;

import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.jianshuo.fiji.core.model.BaseModel;

/**
 * @author yuzhen <yuzhen@kuaishou.com>
 * Created on 2018-07-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "fj_role")
public class Role extends BaseModel {

    private String name;
    private String description;
    private Boolean available;

    @Transient
    private Integer selected;
}
