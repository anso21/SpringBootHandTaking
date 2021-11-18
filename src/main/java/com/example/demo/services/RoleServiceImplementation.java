package com.example.demo.services;

import com.example.demo.entities.Role;
import com.example.demo.entities.Role;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RoleServiceImplementation implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getRole(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role updateRole(Long id, Role role) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            Role t = optional.get();
            if (role.getName() != null && !Objects.equals(t.getName(), role.getName())) {
                t.setName(role.getName());
                return roleRepository.save(t);
            }
        };
        return null;
    }

    @Override
    public Boolean deleteRole(Long id) {
        boolean exists = roleRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Role does not exists");
        };
        roleRepository.deleteById(id);
        return true;
    }
}
