package com.axsosacademy.javaprojectcertificatemanager.controllers;

import com.axsosacademy.javaprojectcertificatemanager.models.LoginUser;
import com.axsosacademy.javaprojectcertificatemanager.models.User;
import com.axsosacademy.javaprojectcertificatemanager.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    // Login Page
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("newLogin", new LoginUser());
        return "login";
    }

    // Login Method
    @PostMapping("/loginUser")
    public String loginUser(
            @Valid @ModelAttribute("newLogin") LoginUser newLogin,
            BindingResult bindingResult,
            HttpSession session
    ) {
        User loggedUser = userService.loginUser(newLogin, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }
        else {
            session.setAttribute("loggedUser", loggedUser);
            Long departmentId = loggedUser.getDepartment().getId();
            if (departmentId == 1) {
                return "redirect:/adminDashboard";
            } else if (departmentId == 2) {
                return "redirect:/teacherDashboard";
            } else if (departmentId == 3) {
                return "redirect:/accountantDashboard";
            }
            return "redirect:/";
        }
    }


    @GetMapping("/adminDashboard")
    public String dashboard(HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        return "adminDashboard";
    }

    @GetMapping("/accountantDashboard")
    public String bootcampDashboard() {
        return "accountantDashboard";
    }

    @GetMapping("teacherDashboard")
    public String teacherDashboard() {
        return "teacherDashboard";
    }

}
