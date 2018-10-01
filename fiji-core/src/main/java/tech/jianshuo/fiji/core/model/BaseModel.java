package tech.jianshuo.fiji.core.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseModel<K> implements Serializable {

    private static final long serialVersionUID = 5017546373479056007L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private K id;

    /**
     * 逻辑删除标识
     */
    @JsonIgnore
    private Long deletedAt = 0L;
    private Long createTime;
    private Long lastModifyTime;

}
