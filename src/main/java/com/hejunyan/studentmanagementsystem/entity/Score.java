package com.hejunyan.studentmanagementsystem.entity;

import jakarta.persistence.*;
import java.math.BigDecimal; // 使用BigDecimal处理分数，更精确

@Entity
@Table(name = "student_scores")
public class Score extends Auditable { // 继承 Auditable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "score", precision = 5, scale = 2) // 对应 DECIMAL(5, 2)
    private BigDecimal score;

    public Score() {
    }

    public Score(Long id, Long studentId, Long courseId, BigDecimal score) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }
    // --- 手动编写所有字段的 Getter 和 Setter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    // --- 手动编写 toString() 方法，方便调试 ---
    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", score=" + score +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}