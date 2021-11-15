package com.example.demo.services;

import com.example.demo.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Teacher createTeacher(Teacher product);
    Optional<Teacher> getTeacher(Long id);
    Teacher updateTeacher(Long id, Teacher product);
    boolean deleteTeacher(Long id);
    Teacher assignCourse(Long courseId, Long teacherId);
}
