package com.hejunyan.studentmanagementsystem.repository;

import com.hejunyan.studentmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 学生数据访问接口
 * JpaRepository<Student, Long> 中：
 *   - Student: 代表这个Repository是用来操作Student这个实体类的
 *   - Long: 代表Student实体类的主键(Id)的数据类型是Long
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // 这个接口现在是空的，但它已经拥有了超能力！
}