package com.axsosacademy.javaprojectcertificatemanager.repositories;

import com.axsosacademy.javaprojectcertificatemanager.models.Department;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findAll();
    Optional<Department> findById(Long id);
}
