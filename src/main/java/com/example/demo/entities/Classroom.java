package com.example.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Course> courses = new HashSet<>();

    public Classroom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Course course) {
        this.courses.add(course);
    }
}
