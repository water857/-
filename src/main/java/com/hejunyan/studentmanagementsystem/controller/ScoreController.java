package com.hejunyan.studentmanagementsystem.controller;

import com.hejunyan.studentmanagementsystem.entity.Score;
import com.hejunyan.studentmanagementsystem.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping
    public Score createScore(@RequestBody Score score) {
        return scoreService.createScore(score);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoreById(@PathVariable Long id) {
        Score score = scoreService.getScoreById(id);
        return ResponseEntity.ok(score);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Score> updateScore(@PathVariable Long id, @RequestBody Score scoreDetails) {
        Score updatedScore = scoreService.updateScore(id, scoreDetails);
        return ResponseEntity.ok(updatedScore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id) {
        scoreService.deleteScore(id);
        return ResponseEntity.noContent().build();
    }

    // 按学生ID查询成绩
    @GetMapping("/student/{studentId}")
    public List<Score> getScoresByStudentId(@PathVariable Long studentId) {
        return scoreService.getScoresByStudentId(studentId);
    }

    // 按课程ID查询成绩
    @GetMapping("/course/{courseId}")
    public List<Score> getScoresByCourseId(@PathVariable Long courseId) {
        return scoreService.getScoresByCourseId(courseId);
    }
}