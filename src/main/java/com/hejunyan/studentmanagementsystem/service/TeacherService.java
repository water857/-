package com.hejunyan.studentmanagementsystem.service;

import com.hejunyan.studentmanagementsystem.entity.Teacher;
import java.util.List;

/**
 * 教师业务逻辑接口
 */
public interface TeacherService {

    List<Teacher> getAllTeachers();

    Teacher getTeacherById(Long id);

    Teacher createTeacher(Teacher teacher);

    Teacher updateTeacher(Long id, Teacher teacherDetails);

    void deleteTeacher(Long id);
}