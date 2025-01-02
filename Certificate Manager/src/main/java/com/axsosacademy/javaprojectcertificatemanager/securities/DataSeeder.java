package com.axsosacademy.javaprojectcertificatemanager.securities;


import com.axsosacademy.javaprojectcertificatemanager.models.Department;
import com.axsosacademy.javaprojectcertificatemanager.models.User;
import com.axsosacademy.javaprojectcertificatemanager.repositories.DepartmentRepository;
import com.axsosacademy.javaprojectcertificatemanager.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DepartmentRepository departmentRepository;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.departmentRepository = departmentRepository;
    }

    @Bean
    CommandLineRunner initUsers() {
        return args -> {
            // Check if the first user already exists
            if (userRepository.findByEmail("admin@example.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@certmanager.com");
                admin.setPassword(passwordEncoder.encode("qwer1234"));// Use BCrypt

                Department adminDepartment = departmentRepository.findById(1L).orElse(null);
                admin.setDepartment(adminDepartment);
                admin.setFirstName("Admin");
                admin.setLastName("Admin");
                admin.setConfirmPassword("qwer1234");
                admin.setPhoneNumber("0561002000");
                userRepository.save(admin);
                System.out.println("Admin user created successfully!");
            } else {
                System.out.println("Admin user already exists!");
            }
        };
    }
}
