package com.hejunyan.studentmanagementsystem.repository;

import com.hejunyan.studentmanagementsystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}