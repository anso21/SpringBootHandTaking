package com.example.demo.controllers;

import com.example.demo.entities.Classroom;
import com.example.demo.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping
    private List<Classroom> getClassroom() {
        return classroomService.getAllClassrooms();
    }

    @GetMapping("{id}")
    private Optional<Classroom> getClassroom(
            @PathVariable Long id
    ) {
        return classroomService.getClassroom(id);
    }

    @DeleteMapping("{id}")
    private boolean deleteClassroom(
            @PathVariable Long id
    ) {
        return classroomService.deleteClassroom(id);
    }

    @PutMapping("{id}")
    private Classroom updateClassroom(
            @PathVariable Long id,
            @RequestBody Classroom classroom
    ) {
        return classroomService.updateClassroom(id, classroom);
    }

    @PostMapping
    private Classroom addClassroom(
            @RequestBody Classroom classroom
    ) {
        return classroomService.createClassroom(classroom);
    }

    @PutMapping("{classroomId}/courses/{courseId}")
    private Classroom assignCourse(
            @PathVariable Long classroomId,
            @PathVariable Long courseId

    ) {
        return classroomService.addCourse(classroomId, courseId);
    }

}
