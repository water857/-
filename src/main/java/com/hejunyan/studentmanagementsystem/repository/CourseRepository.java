package com.hejunyan.studentmanagementsystem.repository;

import com.hejunyan.studentmanagementsystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}