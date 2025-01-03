package com.axsosacademy.javaprojectcertificatemanager.controllers;

import com.axsosacademy.javaprojectcertificatemanager.models.*;
import com.axsosacademy.javaprojectcertificatemanager.services.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AdminController {

    private final UserService userService;
    private final DepartmentService departmentService;
    private final StudentService studentService;
    private final BootcampService bootcampService;
    private final CertificateService certificateService;
    public AdminController(
            UserService userService,
            DepartmentService departmentService,
            StudentService studentService,
            BootcampService bootcampService,
            CertificateService certificateService
    ) {
        this.userService = userService;
        this.departmentService = departmentService;
        this.studentService = studentService;
        this.bootcampService = bootcampService;
        this.certificateService = certificateService;
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

    @GetMapping("/teachers/edit/{id}")
    public String editTeacher(@PathVariable Long id, Model model) {
        // Find the teacher by id
        User user = userService.findById(id);
        model.addAttribute("teacher", user);  // Add teacher to the model for the form
        return "teacher_edit";  // Return the view for editing
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
    public String bootcamps(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        List<Bootcamp> bootcamps = bootcampService.getAllBootcamps();
        model.addAttribute("bootcamps", bootcamps);
        model.addAttribute("newBootcamp", new Bootcamp());
        return "bootcamp_add_table";
    }

    @PostMapping("bootcamps/addBootcamp")
    public String addBootcamp(
            @Valid @ModelAttribute("newBootcamp") Bootcamp newBootcamp,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            List<Bootcamp> bootcamps = bootcampService.getAllBootcamps();
            model.addAttribute("bootcamps", bootcamps);
            return "bootcamp_add_table";
        }
        else {
            bootcampService.addBootcamp(newBootcamp);
            return "redirect:/bootcamps";
        }
    }

    // Certificates Page
    @GetMapping("/certificates")
    public String certificates(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        List<Certificate> certificates = certificateService.getAllCertificates();
        model.addAttribute("certificates", certificates);
        model.addAttribute("newCertificate", new Certificate());
        return "certificate_add_table";
    }

    @PostMapping("/certificates/addCertificate")
    public String addCertificate(
            @Valid @ModelAttribute("newCertificate") Certificate newCertificate,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            List<Certificate> certificates = certificateService.getAllCertificates();
            model.addAttribute("certificates", certificates);
            return "certificate_add_table";
        }
        else {
            certificateService.addCertificate(newCertificate);
            return "redirect:/certificates";
        }
    }
}
