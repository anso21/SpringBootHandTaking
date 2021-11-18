package com.example.demo.controllers;

import com.example.demo.entities.Role;
import com.example.demo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    private List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    private Role addRole(@RequestBody Role role) {
        System.out.println(role.getId());
        return roleService.createRole(role);
    }

    @GetMapping("{id}")
    private Optional<Role> getRole(
            @PathVariable Long id
    ) {
        return roleService.getRole(id);
    }

    @DeleteMapping("{id}")
    private boolean deleteRole(
            @PathVariable Long id
    ) {
        return roleService.deleteRole(id);
    }


    @PutMapping("{id}")
    private Role updateRole(
            @PathVariable Long id,
            @RequestBody Role role
    ) {
        return roleService.updateRole(id, role);
    }


}
