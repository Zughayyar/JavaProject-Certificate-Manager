package com.axsosacademy.javaprojectcertificatemanager.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {

    // Admin Dashboard
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
}
