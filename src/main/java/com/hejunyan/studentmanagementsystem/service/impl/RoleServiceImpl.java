package com.hejunyan.studentmanagementsystem.service.impl;

import com.hejunyan.studentmanagementsystem.entity.Role;
import com.hejunyan.studentmanagementsystem.repository.RoleRepository;
import com.hejunyan.studentmanagementsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}