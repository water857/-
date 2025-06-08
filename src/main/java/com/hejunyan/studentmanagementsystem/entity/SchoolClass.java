package com.hejunyan.studentmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "classes")
public class SchoolClass extends Auditable { // 同样继承 Auditable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "grade")
    private String grade;

    public SchoolClass() {
    }

    public SchoolClass(Long id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    // --- 手动编写所有字段的 Getter 和 Setter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}