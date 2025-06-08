package com.hejunyan.studentmanagementsystem.service;

import com.hejunyan.studentmanagementsystem.entity.Student;
import java.util.List;

/**
 * 学生业务逻辑接口
 */
public interface StudentService {

    /**
     * 获取所有学生列表
     * @return 学生列表
     */
    List<Student> getAllStudents();

    /**
     * 创建一个新学生
     * @param student 要创建的学生对象
     * @return 已保存的学生对象（包含数据库生成的ID）
     */
    Student createStudent(Student student);

    /**
     * 根据ID获取单个学生信息
     * @param id 学生ID
     * @return 查找到的学生对象
     */
    Student getStudentById(Long id);

    /**
     * 根据ID更新学生信息
     * @param id 学生ID
     * @param studentDetails 包含更新信息的学生对象
     * @return 更新后的学生对象
     */
    Student updateStudent(Long id, Student studentDetails);

    /**
     * 根据ID删除学生
     * @param id 学生ID
     */
    void deleteStudent(Long id);
}