package com.example.demo.controllers;

import com.example.demo.entities.Teacher;
import com.example.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    private List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping
    private Teacher addTeacher(@RequestBody Teacher teacher) {
        System.out.println(teacher.getId());
        return teacherService.createTeacher(teacher);
    }

    @PutMapping("{teacherId}/courses/{courseId}")
    private Teacher assignCourse(
            @PathVariable Long teacherId,
            @PathVariable Long courseId

    ) {
        return teacherService.assignCourse(courseId, teacherId);
    }

    @GetMapping("{id}")
    private Optional<Teacher> getTeacher(
            @PathVariable Long id
    ) {
        return teacherService.getTeacher(id);
    }

    @DeleteMapping("{id}")
    private boolean deleteTeacher(
            @PathVariable Long id
    ) {
        return teacherService.deleteTeacher(id);
    }


    @PutMapping("{id}")
    private Teacher updateTeacher(
            @PathVariable Long id,
            @RequestBody Teacher teacher
    ) {
        return teacherService.updateTeacher(id, teacher);
    }


}
