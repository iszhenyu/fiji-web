package tech.jianshuo.fiji.biz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.biz.model.user.Resource;
import tech.jianshuo.fiji.biz.service.ResourceService;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {

    @Override
    public List<Resource> loadUrlAndPermissions() {
        // TODO
        return null;
    }
}
