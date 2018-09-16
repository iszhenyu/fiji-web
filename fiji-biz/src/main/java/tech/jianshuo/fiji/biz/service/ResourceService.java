package tech.jianshuo.fiji.biz.service;

import java.util.List;

import tech.jianshuo.fiji.biz.model.user.Resource;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
public interface ResourceService {

    Resource createResource(Resource resource);

    List<Resource> loadByUserId(Long userId);

    /**
     * 获取资源的url和permission
     */
    List<Resource> loadUrlAndPermissions();


}
