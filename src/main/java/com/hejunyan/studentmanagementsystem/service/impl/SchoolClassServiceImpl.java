package com.hejunyan.studentmanagementsystem.service.impl;

import com.hejunyan.studentmanagementsystem.entity.SchoolClass;
import com.hejunyan.studentmanagementsystem.exception.ResourceNotFoundException;
import com.hejunyan.studentmanagementsystem.repository.SchoolClassRepository;
import com.hejunyan.studentmanagementsystem.service.SchoolClassService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;

    public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    public List<SchoolClass> getAllSchoolClasses() {
        return schoolClassRepository.findAll();
    }

    @Override
    public SchoolClass getSchoolClassById(Long id) {
        return schoolClassRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + id));
    }

    @Override
    public SchoolClass createSchoolClass(SchoolClass schoolClass) {
        // 在这里可以添加业务逻辑，比如检查班级名是否重复等，目前我们直接保存
        return schoolClassRepository.save(schoolClass);
    }

    @Override
    public SchoolClass updateSchoolClass(Long id, SchoolClass schoolClassDetails) {
        // 1. 先找到要更新的班级记录，找不到则抛出404异常
        SchoolClass existingClass = getSchoolClassById(id);

        // 2. 将新数据更新到旧记录上
        existingClass.setName(schoolClassDetails.getName());
        existingClass.setGrade(schoolClassDetails.getGrade());
        // createdAt 和 updatedAt 会被自动处理

        // 3. 保存，JPA会自动执行UPDATE
        return schoolClassRepository.save(existingClass);
    }

    @Override
    public void deleteSchoolClass(Long id) {
        // 1. 检查是否存在，不存在则抛出404异常
        if (!schoolClassRepository.existsById(id)) {
            throw new ResourceNotFoundException("Class not found with id: " + id);
        }
        // 2. 如果存在，则删除
        schoolClassRepository.deleteById(id);
    }
}