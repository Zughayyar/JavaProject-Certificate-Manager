package com.axsosacademy.javaprojectcertificatemanager.services;

import com.axsosacademy.javaprojectcertificatemanager.models.Role;
import com.axsosacademy.javaprojectcertificatemanager.models.User;
import com.axsosacademy.javaprojectcertificatemanager.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Get Role by ID:
    public Role getDepartmentById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    // Get All Roles
    public List<Role> getAllDepartments() {
        return roleRepository.findAll();
    }

    // Get All Users in Role Teachers
    public List<User> getAllTeachers() {
        Role teacherRole = roleRepository.findById(2L).orElse(null);
        if (teacherRole == null) {
            return null;
        }
        return teacherRole.getUsers();
    }

    // Get All Users in Accountant Role:
    public List<User> getAllAccountants() {
        Role accountantRole = roleRepository.findById(3L).orElse(null);
        if (accountantRole == null) {
            return null;
        }
        return accountantRole.getUsers();
    }
}
