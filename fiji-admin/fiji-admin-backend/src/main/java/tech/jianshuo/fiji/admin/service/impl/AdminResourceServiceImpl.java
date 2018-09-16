package tech.jianshuo.fiji.admin.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.admin.service.AdminResourceService;
import tech.jianshuo.fiji.biz.model.user.Resource;
import tech.jianshuo.fiji.biz.service.impl.ResourceServiceImpl;
import tech.jianshuo.fiji.core.model.page.Pagination;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
@Slf4j
@Service
public class AdminResourceServiceImpl extends ResourceServiceImpl implements AdminResourceService {

    @Override
    public Pagination<Resource> loadAllResourcesByPage(int currentPage, int pageSize) {
        return null;
    }
}
