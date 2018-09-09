package tech.jianshuo.fiji.biz.model.user;

import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.jianshuo.fiji.core.model.BaseModel;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "fj_resource")
public class Resource extends BaseModel {
    private static final long serialVersionUID = -7216674797590670244L;

    private String name;
    private String type;
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
    private Resource parent;
    @Transient
    private List<Resource> nodes;

}
