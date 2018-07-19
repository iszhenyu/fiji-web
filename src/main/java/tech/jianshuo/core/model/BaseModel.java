package tech.jianshuo.core.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
@Setter
@Getter
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 5017546373479056007L;

    protected Long id;
    protected Boolean isDeleted;       // 删除标记
    protected Long createTime; 	       // 创建时间
    protected Long lastModifyTime;     // 修改时间

}
