package tech.jianshuo.fiji.biz.persistence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;
import tech.jianshuo.fiji.biz.persistence.mapper.admin.AdminUserMapper;

/**
 * @author zhenyu
 * @date 2018-10-15
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdminUserMapperTest {

    @Autowired
    private AdminUserMapper userMapper;

    @Test
    public void test1() {
        Assert.assertEquals("yuzhen", userMapper.findByUsername("zhenyu").getNickname());
    }

}
