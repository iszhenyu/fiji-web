package tech.jianshuo.fiji.admin.service;

import tech.jianshuo.fiji.biz.model.admin.AdminResource;
import tech.jianshuo.fiji.core.model.page.Pagination;

import java.util.List;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
public interface AdminResourceService {

    Pagination<AdminResource> loadAllResourcesByPage(int pageNo, int pageSize);

    AdminResource createResource(AdminResource adminResource);

    List<AdminResource> loadResourcesByUserId(Long userId);

    List<AdminResource> loadUrlAndPermissions();
}
