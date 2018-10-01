package tech.jianshuo.fiji.biz.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.persistence.mapper.admin.AdminResourceMapper;
import tech.jianshuo.fiji.biz.model.admin.AdminResource;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Component
public class AdminResourceDao extends BizDao<AdminResource> {

    @Autowired
    private AdminResourceMapper adminResourceMapper;

    @Override
    public AdminResourceMapper getMapper() {
        return adminResourceMapper;
    }
}
