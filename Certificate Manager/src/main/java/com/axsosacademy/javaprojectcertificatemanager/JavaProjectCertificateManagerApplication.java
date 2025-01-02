package com.axsosacademy.javaprojectcertificatemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaProjectCertificateManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaProjectCertificateManagerApplication.class, args);
    }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}