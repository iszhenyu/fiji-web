package tech.jianshuo.fiji.biz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.dao.mapper.ResourceMapper;
import tech.jianshuo.fiji.biz.model.user.Resource;
import tech.jianshuo.fiji.core.orm.BaseDao;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Component
public class ResourceDao extends BaseDao<Resource, Long> {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public BaseMapper<Resource> getMapper() {
        return resourceMapper;
    }
}
