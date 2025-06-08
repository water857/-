package com.hejunyan.studentmanagementsystem.service;

import com.hejunyan.studentmanagementsystem.dto.UserDto;
import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserDto> findAllUsers();
    UserDto findUserById(Long id);
    UserDto updateUserRoles(Long id, Set<String> roleNames);
    void deleteUser(Long id);
}