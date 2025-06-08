package com.hejunyan.studentmanagementsystem.controller;

import com.hejunyan.studentmanagementsystem.dto.JwtResponse;
import com.hejunyan.studentmanagementsystem.dto.LoginRequest;
import com.hejunyan.studentmanagementsystem.dto.SignUpRequest;
import com.hejunyan.studentmanagementsystem.entity.Role;
import com.hejunyan.studentmanagementsystem.entity.User;
import com.hejunyan.studentmanagementsystem.repository.RoleRepository;
import com.hejunyan.studentmanagementsystem.repository.UserRepository;
import com.hejunyan.studentmanagementsystem.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        // 创建新用户
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        // 默认分配为学生角色
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_STUDENT")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    // 方便测试用的，用于预置角色和管理员
    @PostMapping("/init")
    public String initRolesAndAdmin() {
        if (roleRepository.findByName("ROLE_STUDENT").isEmpty()) {
            roleRepository.save(new Role(){{ setName("ROLE_STUDENT"); }});
        }
        if (roleRepository.findByName("ROLE_TEACHER").isEmpty()) {
            roleRepository.save(new Role(){{ setName("ROLE_TEACHER"); }});
        }
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new Role(){{ setName("ROLE_ADMIN"); }});
        }

        if (!userRepository.existsByUsername("admin")) {
            User admin = new User("admin", encoder.encode("admin123"));
            Set<Role> roles = new HashSet<>();
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            admin.setRoles(roles);
            userRepository.save(admin);
            return "Admin user and roles initialized.";
        }
        return "Already initialized.";
    }
}