package com.example.demo.services;

import com.example.demo.entities.Classroom;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {

    List<Classroom> getAllClassrooms();
    Classroom createClassroom(Classroom classroom);
    Optional<Classroom> getClassroom(Long id);
    Classroom updateClassroom(Long id, Classroom classroom);
    boolean deleteClassroom(Long id);
    Classroom addCourse(Long classroomId, Long courseId);
}
