package com.axsosacademy.javaprojectcertificatemanager.initializers;

import com.axsosacademy.javaprojectcertificatemanager.models.Role;
import com.axsosacademy.javaprojectcertificatemanager.models.User;
import com.axsosacademy.javaprojectcertificatemanager.repositories.RoleRepository;
import com.axsosacademy.javaprojectcertificatemanager.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            Role adminRole = new Role("ADMIN","ROLE_ADMIN");
            roleRepository.save(adminRole);
            System.out.println("Admin role created: " + adminRole.getRoleName());
            Role teacherRole = new Role("TEACHER","ROLE_TEACHER");
            roleRepository.save(teacherRole);
            System.out.println("Teacher role created: " + teacherRole.getRoleName());
            Role accountantRole = new Role("ACCOUNTANT","ROLE_ACCOUNTANT");
            roleRepository.save(accountantRole);
            System.out.println("Accountant role created: " + accountantRole.getRoleName());
        }

        String email = "admin@certmanager.com";
        String plainPassword = "qwer1234";

        // Check if the user already exists
        if (userRepository.findByEmail(email).isEmpty()) {
            // Hash the password
            String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

            // Create and save the user
            User admin = new User("Admin", "Admin", email, hashedPassword, plainPassword, "1234567890");
            Role adminRole = roleRepository.findAll().get(0);
            admin.getRoles().add(adminRole);
            userRepository.save(admin);
            System.out.println("Admin user created: " + email);
        } else {
            System.out.println("Admin user already exists: " + email);
        }
    }

}
