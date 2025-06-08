package com.hejunyan.studentmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // Java序列化需要
    private static final long serialVersionUID = 1L;

    // 保留这个构造函数，用于简单的 "Role not found: XXX" 这样的消息
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // 这就是我们需要的、更强大的新构造函数！
    // 它会接收资源名（如"User"）、字段名（如"id"）、字段值（如1L）
    // 然后自动格式化成一个清晰的错误信息，例如: "User not found with id : '1'"
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}