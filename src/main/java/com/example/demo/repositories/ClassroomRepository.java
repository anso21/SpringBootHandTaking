package com.example.demo.repositories;

import com.example.demo.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
