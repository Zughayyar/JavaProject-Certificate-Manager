package com.axsosacademy.javaprojectcertificatemanager.controllers;

import com.axsosacademy.javaprojectcertificatemanager.models.Department;
import com.axsosacademy.javaprojectcertificatemanager.models.Student;
import com.axsosacademy.javaprojectcertificatemanager.models.User;
import com.axsosacademy.javaprojectcertificatemanager.services.DepartmentService;
import com.axsosacademy.javaprojectcertificatemanager.services.StudentService;
import com.axsosacademy.javaprojectcertificatemanager.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AdminController {

    private final UserService userService;
    private final DepartmentService departmentService;
    private final StudentService studentService;
    public AdminController(
            UserService userService,
            DepartmentService departmentService,
            StudentService studentService
    ) {
        this.userService = userService;
        this.departmentService = departmentService;
        this.studentService = studentService;
    }

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
    public String teachers(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        List<User> teachers = departmentService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        model.addAttribute("newTeacher", new User());
        return "teacher_add_table";
    }

    // Add Teacher Method:
    @PostMapping("/teachers/addTeacher")
    public String addTeacher(
            @Valid @ModelAttribute("newTeacher") User newTeacher,
            BindingResult bindingResult,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            List<User> teachers = departmentService.getAllTeachers();
            model.addAttribute("teachers", teachers);
            return "teacher_add_table";
        }
        else {
            Department teacherDepartment = departmentService.getDepartmentById(2L);
            newTeacher.setDepartment(teacherDepartment);
            userService.registerUser(newTeacher, bindingResult);
            return "redirect:/teachers";
        }
    }

    // Teachers Edit page
    @GetMapping("/teachers/edit/")
    public String editTeachers(Model model) {
        return "teacher_edit";
    }

    // Accountant Page and Add
    @GetMapping("/accountants")
    public String accountants(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        model.addAttribute("accountants", departmentService.getAllAccountants());
        model.addAttribute("newAccountant", new User());
        return "financial_add_table";
    }

    // Add Accountant
    @PostMapping("/accountants/addAccountant")
    public String addAccountant(
            @Valid @ModelAttribute("newAccountant") User newAccountant,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accountants", departmentService.getAllAccountants());
            return "financial_add_table";
        }
        else {
            Department accountantDepartment = departmentService.getDepartmentById(3L);
            newAccountant.setDepartment(accountantDepartment);
            userService.registerUser(newAccountant, bindingResult);
            return "redirect:/accountants";
        }
    }

    // Edit Accountant Page
    @GetMapping("/accountants/edit")
    public String editAccountants(Model model) {
        return "editfinancial";
    }

    // Students Page and Add
    @GetMapping("/students")
    public String students(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("newStudent", new Student());
        return "student_add_table";
    }

    @PostMapping("/students/addStudent")
    public String addStudent(
            @Valid @ModelAttribute("newStudent") Student newStudent,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "student_add_table";
        }
        else {
            studentService.addStudent(newStudent);
            return "redirect:/students";
        }
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
