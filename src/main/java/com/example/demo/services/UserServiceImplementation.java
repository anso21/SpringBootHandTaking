package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        Optional<User> optional = userRepository.findByEmail(user.getEmail());
        if (optional.isPresent()) {
            throw new IllegalStateException("Email has already taken.");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isEmpty()) return null;

        User userToBeUpdated = optional.get();

        if (user.getEmail() != null && !Objects.equals(user.getEmail(), userToBeUpdated.getEmail())) {
            userToBeUpdated.setEmail(user.getEmail());
        }
        if (user.getUsername() != null && !Objects.equals(user.getUsername(), userToBeUpdated.getUsername())) {
            userToBeUpdated.setUsername(user.getUsername());
        }
        if (user.getFullName() != null && !Objects.equals(user.getFullName(), userToBeUpdated.getFullName())) {
            userToBeUpdated.setFullName(user.getFullName());
        }
        if (user.getPassword() != null && !Objects.equals(user.getPassword(), userToBeUpdated.getPassword())) {
            userToBeUpdated.setFullName(user.getFullName());
        }

        return userRepository.save(userToBeUpdated);
    }

    @Override
    public Boolean deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User does not exist");
        };
        userRepository.deleteById(id);
        return true;
    }
}
