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
public class TeacherServiceImplementation implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseService courseService;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Optional<Teacher> getTeacher(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {
        Optional<Teacher> optional = teacherRepository.findById(id);
        if (optional.isPresent()) {
            Teacher t = optional.get();
            if (teacher.getName() != null && !Objects.equals(t.getName(), teacher.getName())) {
                t.setName(teacher.getName());
                return teacherRepository.save(t);
            }
        };
        return null;
    }

    @Override
    public boolean deleteTeacher(Long id) {
        boolean exists = teacherRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Teacher does not exist");
        };
        teacherRepository.deleteById(id);
        return true;
    }

    @Override
    public Teacher assignCourse(Long courseId, Long teacherId) {
        Course course = courseService.getCourse(courseId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();
        teacher.assignCourse(course);
        course.setTeacher(teacher);
        return teacherRepository.save(teacher);
    }
}
