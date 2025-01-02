//package com.axsosacademy.javaprojectcertificatemanager.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
//import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
//
//@Configuration
//public class WebSecurityConfig {
//
//    @Autowired
//    HandlerMappingIntrospector introspector;
//
//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http
//                .authorizeHttpRequests(
//                        auth -> auth.requestMatchers(
//                                        new MvcRequestMatcher(introspector, "/"),
//                                        new MvcRequestMatcher(introspector, "/home")
//                                ).authenticated()
//                                .anyRequest().permitAll()
//                )
//                .formLogin(
//                        form -> form.loginPage("/login")
//                                .defaultSuccessUrl("/home")
//                                .permitAll()
//                )
//                .logout(
//                        logout -> logout.permitAll()
//                );
//        return http.build();
//    }
//
//}
