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

    //


    @GetMapping("/adminDashboard")
    public String dashboard(HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        return "adminDashboard";
    }

    // Teachers Page and Add Teacher
    @GetMapping("/teachers")
    public String teachers(Model model) {
        return "teacher_add_table";
    }

    // Teachers Edit page
    @GetMapping("/teachers/edit/")
    public String editTeachers(Model model) {
        return "teacher_edit";
    }

    // Accountant Page and Add
    @GetMapping("/accountants")
    public String accountants(Model model) {
        return "financial_add_table";
    }

    // Edit Accountant Page
    @GetMapping("/accountants/edit")
    public String editAccountants(Model model) {
        return "editfinancial";
    }

    // Students Page and Add
    @GetMapping("/students")
    public String students(Model model) {
        return "student_add_table";
    }

    // Student Edit page
    @GetMapping("/students/edit")
    public String editStudents(Model model) {
        return "student_edit";
    }

    // Bootcamp Add page
    @GetMapping("/bootcamps")
    public String bootcamps(Model model) {
        return "bootcamp_add_table";
    }

    // Certificates Page
    @GetMapping("/certificates")
    public String certificates(Model model) {
        return "certificate_add_table";
    }





    /// ////////////
    @GetMapping("/accountantDashboard")
    public String bootcampDashboard() {
        return "accountantDashboard";
    }

    @GetMapping("teacherDashboard")
    public String teacherDashboard() {
        return "teacherDashboard";
    }


    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
