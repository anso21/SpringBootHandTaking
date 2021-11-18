package com.example.demo.services;

import com.example.demo.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    User createUser(User user);
    Optional<User> getUser(Long id);
    User getByUsername(String username);
    User updateUser(Long id, User user);
    Boolean deleteUser(Long id);
    User addRoleToUser(Long userId, Long roleId);


}
