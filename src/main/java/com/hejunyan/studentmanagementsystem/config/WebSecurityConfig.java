package com.hejunyan.studentmanagementsystem.config;

import com.hejunyan.studentmanagementsystem.security.AuthEntryPointJwt;
import com.hejunyan.studentmanagementsystem.security.AuthTokenFilter;
import com.hejunyan.studentmanagementsystem.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 禁用CSRF
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler)) // 设置异常处理
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 无状态session
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // 认证接口（登录注册）对所有人开放

                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // 所有/api/admin/下的路径都需要ADMIN角色

                        // 学生管理：ADMIN可以做任何事, TEACHER可以查看, STUDENT只能看自己的
                        .requestMatchers(HttpMethod.GET, "/api/students", "/api/students/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers("/api/students/**").hasRole("ADMIN")

                        // 教师管理：只有ADMIN可以
                        .requestMatchers("/api/teachers/**").hasRole("ADMIN")

                        // 班级管理：只有ADMIN可以
                        .requestMatchers("/api/classes/**").hasRole("ADMIN")

                        // 课程管理：ADMIN和TEACHER可以
                        .requestMatchers("/api/courses/**").hasAnyRole("ADMIN", "TEACHER")

                        // 成绩管理：ADMIN和TEACHER可以
                        .requestMatchers("/api/scores/**").hasAnyRole("ADMIN", "TEACHER")

                        // 其他任何请求都需要认证
                        .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}