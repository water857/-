package com.hejunyan.studentmanagementsystem.service.impl;

import com.hejunyan.studentmanagementsystem.entity.Course;
import com.hejunyan.studentmanagementsystem.exception.ResourceNotFoundException;
import com.hejunyan.studentmanagementsystem.repository.CourseRepository;
import com.hejunyan.studentmanagementsystem.service.CourseService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    @Override
    public Course createCourse(Course course) {
        // 在实际业务中，我们应该在这里检查 teacherId 是否真实存在于 teachers 表中
        // 但为了保持流程简洁，我们暂时省略这一步
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course courseDetails) {
        Course existingCourse = getCourseById(id);

        existingCourse.setName(courseDetails.getName());
        existingCourse.setTeacherId(courseDetails.getTeacherId());

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }
}