package com.xiaomianshi.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author zhen.yu
 * @since 2018/7/1
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -7872232050580884961L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
