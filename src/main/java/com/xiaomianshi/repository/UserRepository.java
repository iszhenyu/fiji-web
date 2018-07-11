package com.xiaomianshi.repository;

import com.xiaomianshi.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByMobile(String mobile);

    User findByEmail(String email);

    User findByUserId(Long userId);
}
