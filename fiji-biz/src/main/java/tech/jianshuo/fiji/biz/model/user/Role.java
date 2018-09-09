package tech.jianshuo.fiji.biz.model.user;

import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.jianshuo.fiji.biz.model.Model;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "fj_role")
public class Role extends Model {
    private static final long serialVersionUID = -1740905784713031285L;

    private String name;
    private String description;
    private Boolean available;

    @Transient
    private Integer selected;
}
