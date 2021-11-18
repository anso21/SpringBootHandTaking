package com.example.demo.services;

import com.example.demo.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();
    Role createRole(Role role);
    Optional<Role> getRole(Long id);
    Role updateRole(Long id, Role role);
    Boolean deleteRole(Long id);
}
