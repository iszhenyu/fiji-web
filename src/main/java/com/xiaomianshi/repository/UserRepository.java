package com.xiaomianshi.repository;

import com.xiaomianshi.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByEmail(String email);

    User findByUserNameOrEmail(String username, String email);

    User findByUserId(Long userId);
}
