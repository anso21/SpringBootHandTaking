package com.example.demo.services;

import com.example.demo.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> getAllCourses();
    Course createCourse(Course course);
    Optional<Course> getCourse(Long id);
    Course updateCourse(Long id, Course course);
    boolean deleteCourse(Long id);
}
