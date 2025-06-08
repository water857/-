package com.hejunyan.studentmanagementsystem.service;

import com.hejunyan.studentmanagementsystem.entity.Course;
import java.util.List;

/**
 * 课程业务逻辑接口
 */
public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course createCourse(Course course);

    Course updateCourse(Long id, Course courseDetails);

    void deleteCourse(Long id);
}