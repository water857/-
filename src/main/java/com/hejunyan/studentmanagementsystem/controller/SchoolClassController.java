package com.hejunyan.studentmanagementsystem.controller;

import com.hejunyan.studentmanagementsystem.entity.SchoolClass;
import com.hejunyan.studentmanagementsystem.service.SchoolClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/classes") // API的基础路径
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    // 创建班级 POST /api/classes
    @PostMapping
    public SchoolClass createSchoolClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.createSchoolClass(schoolClass);
    }

    // 获取所有班级 GET /api/classes
    @GetMapping
    public List<SchoolClass> getAllSchoolClasses() {
        return schoolClassService.getAllSchoolClasses();
    }

    // 获取单个班级 GET /api/classes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<SchoolClass> getSchoolClassById(@PathVariable Long id) {
        SchoolClass schoolClass = schoolClassService.getSchoolClassById(id);
        return ResponseEntity.ok(schoolClass);
    }

    // 更新班级 PUT /api/classes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<SchoolClass> updateSchoolClass(@PathVariable Long id, @RequestBody SchoolClass schoolClassDetails) {
        SchoolClass updatedClass = schoolClassService.updateSchoolClass(id, schoolClassDetails);
        return ResponseEntity.ok(updatedClass);
    }

    // 删除班级 DELETE /api/classes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchoolClass(@PathVariable Long id) {
        schoolClassService.deleteSchoolClass(id);
        return ResponseEntity.noContent().build();
    }
}