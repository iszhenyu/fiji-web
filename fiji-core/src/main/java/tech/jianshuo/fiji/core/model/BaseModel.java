package tech.jianshuo.fiji.core.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 5017546373479056007L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long deletedAt = 0L; // 删除标记
    private Long createTime;     // 创建时间
    private Long lastModifyTime; // 修改时间

}
