package tech.jianshuo.fiji.biz.model.admin;

import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.jianshuo.fiji.biz.constant.ResourceType;
import tech.jianshuo.fiji.biz.model.Model;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "fj_admin_resource")
public class AdminResource extends Model {
    private static final long serialVersionUID = -7216674797590670244L;

    private String name;
    private ResourceType type;
    private String url;
    private String icon;
    private String permission;
    private Long parentId;
    private Integer sort;
    private Boolean external;
    private Boolean available;

    @Transient
    private String checked;
    @Transient
    private AdminResource parent;
    @Transient
    private List<AdminResource> nodes;

}
