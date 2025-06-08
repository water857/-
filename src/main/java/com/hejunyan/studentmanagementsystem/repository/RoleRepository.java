package com.hejunyan.studentmanagementsystem.repository;

import com.hejunyan.studentmanagementsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}