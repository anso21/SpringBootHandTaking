package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public Optional<User> getUser(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User  user) {
        return userService.createUser(user);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable(value = "id") Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public boolean deleteUser(@PathVariable(value = "id") Long id) {
        return userService.deleteUser(id);
    }

}
