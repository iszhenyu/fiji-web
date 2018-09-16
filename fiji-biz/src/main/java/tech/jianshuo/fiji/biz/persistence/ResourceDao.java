package tech.jianshuo.fiji.biz.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.persistence.mapper.ResourceMapper;
import tech.jianshuo.fiji.biz.model.user.Resource;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Component
public class ResourceDao extends BizDao<Resource> {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public ResourceMapper getMapper() {
        return resourceMapper;
    }
}
