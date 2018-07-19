package tech.jianshuo.core.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
@Setter
@Getter
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 5017546373479056007L;

    private Long id;
    private Boolean isDeleted; // 删除标记
    private Long createTime; // 创建时间
    private Long lastModifyTime; // 修改时间

}
