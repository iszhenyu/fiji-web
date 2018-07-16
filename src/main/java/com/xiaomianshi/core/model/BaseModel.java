package com.xiaomianshi.core.model;

import java.io.Serializable;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 5017546373479056007L;

    protected Long id;
    protected Boolean isDeleted;       // 删除标记
    protected Long createTime; 	       // 创建时间
    protected Long lastModifyTime;     // 修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
