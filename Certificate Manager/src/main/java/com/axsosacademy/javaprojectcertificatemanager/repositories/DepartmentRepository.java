package com.axsosacademy.javaprojectcertificatemanager.repositories;

import com.axsosacademy.javaprojectcertificatemanager.models.Department;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findAll();
}
