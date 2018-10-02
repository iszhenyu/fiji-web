package tech.jianshuo.fiji.admin.service.impl;

import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.jianshuo.fiji.admin.service.UserAdminService;
import tech.jianshuo.fiji.admin.util.Paginations;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.persistence.UserDao;
import tech.jianshuo.fiji.biz.service.impl.UserServiceImpl;
import tech.jianshuo.fiji.core.model.page.Pagination;

/**
 * @author zhenyu
 * @date 2018-10-03
 */
@Slf4j
@Primary
@Service
public class UserAdminServiceImpl extends UserServiceImpl implements UserAdminService {

    @Autowired
    private UserDao userDao;

    @Override
    public Pagination<User> loadAllUsersByPage(int pageNo, int pageSize) {
        Page<User> page = Paginations.startPage(pageNo, pageSize).doSelectPage(() -> userDao.findAll());
        return Paginations.fromPage(page);
    }
}
