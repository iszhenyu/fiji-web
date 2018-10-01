package tech.jianshuo.fiji.admin.service;

import tech.jianshuo.fiji.biz.model.admin.AdminResource;
import tech.jianshuo.fiji.biz.service.ResourceService;
import tech.jianshuo.fiji.core.model.page.Pagination;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
public interface AdminResourceService extends ResourceService {

    Pagination<AdminResource> loadAllResourcesByPage(int pageNo, int pageSize);

}
