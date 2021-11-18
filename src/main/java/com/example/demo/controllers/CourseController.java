package com.example.demo.controllers;

import com.example.demo.entities.Course;
import com.example.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    private List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("{id}")
    private Optional<Course> getClassroom(
            @PathVariable Long id
    ) {
        return courseService.getCourse(id);
    }

    @PutMapping("{id}")
    private Course updateCourse(
            @PathVariable Long id,
            @RequestBody Course course
    ) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("{id}")
    private boolean deleteCourse(
            @PathVariable Long id
    ) {
        return courseService.deleteCourse(id);
    }

    @PostMapping
    private Course addCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PostMapping("teachers/{teacherId}")
    private Course addTeacher(
            @PathVariable Long teacherId,
            @RequestBody Course course) {
        return courseService.createCourseAndAssignToTeacher(course, teacherId);
    }

}
