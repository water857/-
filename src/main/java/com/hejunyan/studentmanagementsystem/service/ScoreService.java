package com.hejunyan.studentmanagementsystem.service;

import com.hejunyan.studentmanagementsystem.entity.Score;
import java.util.List;

public interface ScoreService {

    Score createScore(Score score);

    Score getScoreById(Long id);

    Score updateScore(Long id, Score scoreDetails);

    void deleteScore(Long id);

    List<Score> getScoresByStudentId(Long studentId);

    List<Score> getScoresByCourseId(Long courseId);
}