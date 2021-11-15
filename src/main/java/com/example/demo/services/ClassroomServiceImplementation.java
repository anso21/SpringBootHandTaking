package com.example.demo.services;

import com.example.demo.entities.Classroom;
import com.example.demo.entities.Course;
import com.example.demo.entities.Teacher;
import com.example.demo.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClassroomServiceImplementation implements ClassroomService{

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    CourseService courseService;

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom createClassroom(Classroom classroom) {
        if (classroom.getName() != null) {
            return classroomRepository.save(classroom);
        }
        return null;
    }

    @Override
    public Optional<Classroom> getClassroom(Long id) {
        return classroomRepository.findById(id);
    }

    @Override
    public Classroom updateClassroom(Long id, Classroom classroom) {
        Optional<Classroom> optional = classroomRepository.findById(id);
        if (optional.isPresent()) {
            Classroom c = optional.get();
            if (classroom.getName() != null && !Objects.equals(c.getName(), classroom.getName())) {
                c.setName(classroom.getName());
                return classroomRepository.save(c);
            }
        };
        return null;
    }

    @Override
    public boolean deleteClassroom(Long id) {
        boolean exists = classroomRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User does not exist");
        };
        classroomRepository.deleteById(id);
        return true;
    }

    @Override
    public Classroom addCourse(Long classroomId, Long courseId) {
        Optional<Course> courseOptional = courseService.getCourse(courseId);
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);

        if (courseOptional.isPresent() && classroomOptional.isPresent()) {
            Course course = courseOptional.get();
            Classroom classroom = classroomOptional.get();

            classroom.setCourses(course);
            course.setClassroom(classroom);

            return classroomRepository.save(classroom);
        }
        return null;
    }

}
