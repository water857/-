package com.hejunyan.studentmanagementsystem.repository;

import com.hejunyan.studentmanagementsystem.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    // Spring Data JPA 的魔法：根据方法名自动生成查询
    // 查找某个学生的所有成绩记录
    List<Score> findByStudentId(Long studentId);

    // 查找某门课程的所有成绩记录
    List<Score> findByCourseId(Long courseId);

    // 查找某个学生某门课程的特定成绩记录 (用于防止重复创建)
    Optional<Score> findByStudentIdAndCourseId(Long studentId, Long courseId);
}