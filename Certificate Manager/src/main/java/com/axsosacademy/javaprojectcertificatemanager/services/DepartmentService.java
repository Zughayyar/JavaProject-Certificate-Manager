package com.axsosacademy.javaprojectcertificatemanager.services;

import com.axsosacademy.javaprojectcertificatemanager.models.Department;
import com.axsosacademy.javaprojectcertificatemanager.models.User;
import com.axsosacademy.javaprojectcertificatemanager.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Get Department by ID:
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    // Get All Departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get All Users in Department Teachers
    public List<User> getAllTeachers() {
        Department teacherDepartment = departmentRepository.findById(2L).orElse(null);
        if (teacherDepartment == null) {
            return null;
        }
        return teacherDepartment.getUsers();
    }

    // Get All Users in Accountant Department:
    public List<User> getAllAccountants() {
        Department accountantDepartment = departmentRepository.findById(3L).orElse(null);
        if (accountantDepartment == null) {
            return null;
        }
        return accountantDepartment.getUsers();
    }
}
