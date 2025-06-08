package com.hejunyan.studentmanagementsystem.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_number", nullable = false, unique = true)
    private String studentNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "class_id")
    private Long classId;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    // ▼▼▼▼▼ 手动添加的部分从这里开始 ▼▼▼▼▼

    // 1. 手动添加无参构造函数 (Jackson反序列化需要)
    public Student() {
    }

    // 2. 手动添加全参构造函数 (可选，但好习惯)
    public Student(Long id, String studentNumber, String name, String gender, Long classId, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.name = name;
        this.gender = gender;
        this.classId = classId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // --- 手动编写的toString方法需要更新，以包含继承的字段 ---
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentNumber='" + studentNumber + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", classId=" + classId +
                ", userId=" + userId +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}