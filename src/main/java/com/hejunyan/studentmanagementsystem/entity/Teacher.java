package com.hejunyan.studentmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher extends Auditable { // 继承 Auditable 以获得创建和更新时间戳

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_number", nullable = false, unique = true)
    private String teacherNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    public Teacher() {
    }

    public Teacher(Long id, String teacherNumber, String name, Long userId) {
        this.id = id;
        this.teacherNumber = teacherNumber;
        this.name = name;
        this.userId = userId;
    }
    // --- 手动编写所有字段的 Getter 和 Setter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // --- 手动编写 toString() 方法，方便调试 ---
    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherNumber='" + teacherNumber + '\'' +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}