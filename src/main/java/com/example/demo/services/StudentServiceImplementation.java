package com.example.demo.services;

import com.example.demo.entities.Student;
import com.example.demo.entities.Course;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    CourseService courseService;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        if (student.getFirstname() != null
                && student.getLastname() != null
                && student.getAge() != null) {
            return studentRepository.save(student);
        }
        return null;
    }

    @Override
    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            Student c = optional.get();
            if (student.getLastname() != null && !Objects.equals(c.getLastname(), student.getLastname())) {
                c.setLastname(student.getLastname());
            }
            if (student.getFirstname() != null && !Objects.equals(c.getFirstname(), student.getFirstname())) {
                c.setFirstname(student.getFirstname());
            }
            if (student.getAge() != null && !Objects.equals(c.getAge(), student.getAge())) {
                c.setAge(student.getAge());
            }
            return studentRepository.save(c);
        };
        return null;
    }

    @Override
    public boolean deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User does not exist");
        };
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public Student addCourse(Long studentId, Long courseId) {
        Optional<Course> courseOptional = courseService.getCourse(courseId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (courseOptional.isPresent() && studentOptional.isPresent()) {
            Course course = courseOptional.get();
            Student student = studentOptional.get();

            student.setCourses(course);
            course.setStudents(student);
            return studentRepository.save(student);
        }
        return null;
    }

}
