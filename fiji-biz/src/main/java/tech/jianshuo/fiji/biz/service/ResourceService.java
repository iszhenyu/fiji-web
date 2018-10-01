package tech.jianshuo.fiji.biz.service;

import java.util.List;

import tech.jianshuo.fiji.biz.model.admin.AdminResource;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
public interface ResourceService {

    AdminResource createResource(AdminResource adminResource);

    List<AdminResource> loadResourcesByUserId(Long userId);

    List<AdminResource> loadUrlAndPermissions();

}
