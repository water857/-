package com.hejunyan.studentmanagementsystem.service;

import com.hejunyan.studentmanagementsystem.entity.SchoolClass;
import java.util.List;

/**
 * 班级业务逻辑接口 - 定义班级模块的所有业务操作
 */
public interface SchoolClassService {

    List<SchoolClass> getAllSchoolClasses();

    SchoolClass getSchoolClassById(Long id);

    SchoolClass createSchoolClass(SchoolClass schoolClass);

    SchoolClass updateSchoolClass(Long id, SchoolClass schoolClassDetails);

    void deleteSchoolClass(Long id);
}