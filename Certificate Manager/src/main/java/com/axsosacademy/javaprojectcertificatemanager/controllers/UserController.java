package com.axsosacademy.javaprojectcertificatemanager.controllers;

import com.axsosacademy.javaprojectcertificatemanager.models.User;
import com.axsosacademy.javaprojectcertificatemanager.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/usersDetails")
    public String usersDetails(Model model) {
        List<User> usersWithRoles = userService.getAllUsersWithRoles();
        List<Object[]> usersDetails = userService.getUsersAndRolesDetails();
        model.addAttribute("usersWithRoles", usersWithRoles);
        model.addAttribute("usersDetails", usersDetails);
        return "usersDetails";
    }
}
