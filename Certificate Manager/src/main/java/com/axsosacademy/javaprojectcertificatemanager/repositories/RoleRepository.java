package com.axsosacademy.javaprojectcertificatemanager.repositories;

import com.axsosacademy.javaprojectcertificatemanager.models.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findAll();
    Optional<Role> findById(Long id);

    // Inner join to retrieve only Roles
    @Query("SELECT r FROM Role r JOIN r.users u")
    List<Role> joinRolesAndUsers();

    // Inner join to retrieve both Roles and Users
    @Query("SELECT r, u FROM Role r JOIN r.users u")
    List<Object[]> joinRolesAndUsersWithDetails();


}
