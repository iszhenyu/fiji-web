package tech.jianshuo.fiji.persistence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.jianshuo.fiji.admin.AdminApplication;
import tech.jianshuo.fiji.biz.persistence.mapper.admin.AdminRoleMapper;
import tech.jianshuo.fiji.biz.persistence.mapper.admin.AdminUserMapper;
import tech.jianshuo.fiji.biz.persistence.mapper.admin.AdminUserRoleMapper;

import java.util.Collections;

/**
 * @author zhenyu
 * @date 2018-10-15
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdminUserServiceTest {

    @Autowired
    private AdminUserRoleMapper userRoleMapper;

    @Test
    public void test1() {
        Assert.assertEquals(2, userRoleMapper.findByUserIds(Collections.singletonList(3L)).size());
    }
}
