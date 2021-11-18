package com.example.demo.services;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User {} was not found", username));
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
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

    @Override
    public User addRoleToUser(Long userId, Long roleId) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (roleOptional.isEmpty() || userOptional.isEmpty()) {
            throw new IllegalStateException("User or role does not exists !");
        }

        User user = userOptional.get();
        user.getRoles().add(roleOptional.get());
        return userRepository.save(user);
    }


}
