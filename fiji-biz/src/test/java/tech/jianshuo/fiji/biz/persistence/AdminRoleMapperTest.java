package tech.jianshuo.fiji.biz.persistence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;
import tech.jianshuo.fiji.biz.persistence.mapper.admin.AdminRoleMapper;

/**
 * @author zhenyu
 * @date 2018-10-15
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdminRoleMapperTest {

    @Autowired
    private AdminRoleMapper roleMapper;

    @Test
    public void test1() {
        Assert.assertEquals(1, roleMapper.findRolesByUserId(3).size());
    }
}
