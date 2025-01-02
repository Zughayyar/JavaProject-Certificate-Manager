package com.axsosacademy.javaprojectcertificatemanager.initializers;

import com.axsosacademy.javaprojectcertificatemanager.models.Department;
import com.axsosacademy.javaprojectcertificatemanager.models.User;
import com.axsosacademy.javaprojectcertificatemanager.repositories.DepartmentRepository;
import com.axsosacademy.javaprojectcertificatemanager.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public UserInitializer(UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        String email = "admin@certmanager.com";
        String plainPassword = "qwer1234";

        // Check if the user already exists
        if (userRepository.findByEmail(email).isEmpty()) {
            // Hash the password
            String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

            // Create and save the user
            User admin = new User(); // Replace with your User model
            admin.setEmail(email);
            admin.setPassword(hashedPassword);
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setConfirmPassword("qwer1234");
            admin.setPhoneNumber("1234567890");
            Department adminDepartment = departmentRepository.findById(1L).orElse(null);
            admin.setDepartment(adminDepartment);
            userRepository.save(admin);
            System.out.println("Admin user created: " + email);
        } else {
            System.out.println("Admin user already exists: " + email);
        }
    }

}
