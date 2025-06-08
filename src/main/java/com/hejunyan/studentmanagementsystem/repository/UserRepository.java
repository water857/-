package com.hejunyan.studentmanagementsystem.repository;

import com.hejunyan.studentmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Security 需要此方法来按用户名加载用户
    Optional<User> findByUsername(String username);

    // 注册时需要检查用户名是否存在
    Boolean existsByUsername(String username);
}