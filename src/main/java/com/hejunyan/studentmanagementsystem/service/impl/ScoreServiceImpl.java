package com.hejunyan.studentmanagementsystem.service.impl;

import com.hejunyan.studentmanagementsystem.entity.Score;
import com.hejunyan.studentmanagementsystem.exception.ResourceNotFoundException;
import com.hejunyan.studentmanagementsystem.repository.CourseRepository;
import com.hejunyan.studentmanagementsystem.repository.ScoreRepository;
import com.hejunyan.studentmanagementsystem.repository.StudentRepository;
import com.hejunyan.studentmanagementsystem.service.ScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final StudentRepository studentRepository; // 注入Student仓库
    private final CourseRepository courseRepository;   // 注入Course仓库

    public ScoreServiceImpl(ScoreRepository scoreRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.scoreRepository = scoreRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Score createScore(Score score) {
        // 1. 验证学生是否存在
        studentRepository.findById(score.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Cannot create score: Student not found with id: " + score.getStudentId()));

        // 2. 验证课程是否存在
        courseRepository.findById(score.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Cannot create score: Course not found with id: " + score.getCourseId()));

        // 3. 验证该学生是否已录入该课程的成绩（基于数据库的UNIQUE约束）
        scoreRepository.findByStudentIdAndCourseId(score.getStudentId(), score.getCourseId())
                .ifPresent(s -> {
                    throw new IllegalStateException("Score for this student and course already exists.");
                });

        return scoreRepository.save(score);
    }

    @Override
    public Score getScoreById(Long id) {
        return scoreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found with id: " + id));
    }

    @Override
    public Score updateScore(Long id, Score scoreDetails) {
        Score existingScore = getScoreById(id);

        // 通常只允许更新分数
        existingScore.setScore(scoreDetails.getScore());

        return scoreRepository.save(existingScore);
    }

    @Override
    public void deleteScore(Long id) {
        if (!scoreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Score not found with id: " + id);
        }
        scoreRepository.deleteById(id);
    }

    @Override
    public List<Score> getScoresByStudentId(Long studentId) {
        // 验证学生是否存在
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }
        return scoreRepository.findByStudentId(studentId);
    }

    @Override
    public List<Score> getScoresByCourseId(Long courseId) {
        // 验证课程是否存在
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found with id: " + courseId);
        }
        return scoreRepository.findByCourseId(courseId);
    }
}