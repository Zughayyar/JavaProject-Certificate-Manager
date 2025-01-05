package com.axsosacademy.javaprojectcertificatemanager.repositories;

import com.axsosacademy.javaprojectcertificatemanager.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByEmail(String email);

    // Inner join to retrieve only the Users
    @Query("SELECT u FROM User u JOIN u.roles r")
    List<User> joinUsersAndRoles();

    // Inner join to retrieve both Users and Roles
    @Query("SELECT u, r FROM User u JOIN u.roles r")
    List<Object[]> joinUsersAndRolesWithDetails();
}
