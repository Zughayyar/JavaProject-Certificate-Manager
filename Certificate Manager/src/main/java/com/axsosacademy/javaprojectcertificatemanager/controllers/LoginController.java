package com.axsosacademy.javaprojectcertificatemanager.controllers;

import com.axsosacademy.javaprojectcertificatemanager.services.UserService;
import com.axsosacademy.javaprojectcertificatemanager.models.LoginUser;
import com.axsosacademy.javaprojectcertificatemanager.models.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // Login Page
    @GetMapping("/")
    public String login(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            model.addAttribute("newLogin", new LoginUser());
            return "login";
        }
        else {
            User loggedUser = (User) session.getAttribute("loggedUser");
            return checkLoginUser(loggedUser);
        }
    }

    private String checkLoginUser(User loggedUser) {
        Long roleId = loggedUser.getRoles().get(0).getId();
        if (roleId == 1) {
            return "redirect:/adminDashboard";
        } else if (roleId == 2) {
            return "redirect:/teacherDashboard";
        } else if (roleId == 3) {
            return "redirect:/accountantDashboard";
        }
        return "redirect:/";
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
            return checkLoginUser(loggedUser);
        }
    }


    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
