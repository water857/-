package com.hejunyan.studentmanagementsystem.service.impl;

import com.hejunyan.studentmanagementsystem.entity.Student;
import com.hejunyan.studentmanagementsystem.exception.ResourceNotFoundException;
import com.hejunyan.studentmanagementsystem.repository.StudentRepository;
import com.hejunyan.studentmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 学生业务逻辑的实现类
 */
@Service
public class StudentServiceImpl implements StudentService {

    // 注入我们之前创建的Repository
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        // 直接调用Repository的findAll方法，获取所有学生
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        // 直接调用Repository的save方法，保存学生信息
        return studentRepository.save(student);
    }
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        // 首先，从数据库找到需要更新的原始学生记录
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        // 将请求体中的新信息，更新到原始记录上
        existingStudent.setStudentNumber(studentDetails.getStudentNumber());
        existingStudent.setName(studentDetails.getName());
        existingStudent.setGender(studentDetails.getGender());
        existingStudent.setClassId(studentDetails.getClassId());
        // 注意：userId, createdAt 通常不应被更新。updatedAt 会被 @PreUpdate 自动处理。

        // 保存更新后的对象，JPA会自动执行UPDATE
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        // 先检查学生是否存在，这比直接查更高效
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        // 如果存在，则执行删除
        studentRepository.deleteById(id);
    }
}