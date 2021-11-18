package com.example.demo.services;

import com.example.demo.entities.Course;
import com.example.demo.entities.Teacher;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseServiceImplementation  implements CourseService{

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course createCourse(Course course) {
        if (course.getName() != null) {
            return courseRepository.save(course);
        }
        return null;    }

    @Override
    public Course createCourseAndAssignToTeacher(Course course, Long teacherId) {
        Optional optional = teacherRepository.findById(teacherId);
        if (optional.isEmpty()) throw new IllegalStateException("Teacher does not exits !");
//        Teacher teacher = (Teacher) optional.get();
        course.setTeacher((Teacher) optional.get());
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> getCourse(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Optional<Course> optional = courseRepository.findById(id);
        if (optional.isPresent()) {
            Course c = optional.get();
            if (course.getName() != null && !Objects.equals(c.getName(), course.getName())) {
                c.setName(course.getName());
                return courseRepository.save(c);
            }
        };
        return null;
    }

    @Override
    public boolean deleteCourse(Long id) {
        boolean exists = courseRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Course does not exist");
        };
        courseRepository.deleteById(id);
        return true;
    }
}
