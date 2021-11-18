package com.example.demo.controllers;

import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    private List<Student> getStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    private Optional<Student> getStudent(
            @PathVariable Long id
    ) {
        return studentService.getStudent(id);
    }

    @DeleteMapping("{id}")
    private boolean deleteStudent(
            @PathVariable Long id
    ) {
        return studentService.deleteStudent(id);
    }

    @PutMapping("{id}")
    private Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student student
    ) {
        return studentService.updateStudent(id, student);
    }

    @PutMapping("{studentId}/courses/{courseId}")
    private Student assignCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId

    ) {
        return studentService.addCourse(studentId, courseId);
    }

    @PostMapping
    private Student addStudent(
            @RequestBody Student student
    ) {
        return studentService.createStudent(student);
    }

    @PostMapping("courses/{courseId}")
    private Student addStudent(
            @RequestBody Student student,
            @PathVariable Long courseId
    ) {
        return studentService.createStudentAndAssignCourse(student, courseId);
    }



}
