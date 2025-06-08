package com.hejunyan.studentmanagementsystem.dto;

// 此类结构与 SignUpRequest 完全相同，但为了语义清晰分开定义
public class LoginRequest {
    private String username;
    private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}