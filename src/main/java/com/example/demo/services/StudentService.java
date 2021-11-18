package com.example.demo.services;

import com.example.demo.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAllStudents();
    Student createStudent(Student student);
    Student createStudentAndAssignCourse(Student student, Long courseId);
    Optional<Student> getStudent(Long id);
    Student updateStudent(Long id, Student student);
    boolean deleteStudent(Long id);
    Student addCourse(Long studentId, Long courseId);
}
