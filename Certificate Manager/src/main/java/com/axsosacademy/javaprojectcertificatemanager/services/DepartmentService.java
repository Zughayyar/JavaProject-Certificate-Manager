package com.axsosacademy.javaprojectcertificatemanager.services;

import com.axsosacademy.javaprojectcertificatemanager.models.Department;
import com.axsosacademy.javaprojectcertificatemanager.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Get All Departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
