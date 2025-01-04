package com.axsosacademy.javaprojectcertificatemanager.controllers;

import com.axsosacademy.javaprojectcertificatemanager.models.*;
import com.axsosacademy.javaprojectcertificatemanager.services.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final StudentService studentService;
    private final BootcampService bootcampService;
    private final CertificateService certificateService;
    private final ApprovalService approvalService;

    public AdminController(
            UserService userService,
            RoleService roleService,
            StudentService studentService,
            BootcampService bootcampService,
            CertificateService certificateService,
            ApprovalService approvalService
    ) {
        this.userService = userService;
        this.roleService = roleService;
        this.studentService = studentService;
        this.bootcampService = bootcampService;
        this.certificateService = certificateService;
        this.approvalService = approvalService;
    }

    // Admin Dashboard
    @GetMapping("/adminDashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        List<Bootcamp> bootcamps = bootcampService.getAllBootcamps();
        Collections.reverse(bootcamps);
        model.addAttribute("bootcamps", bootcamps);
        return "adminDashboard";
    }


    // Teachers Page and Add Teacher
    @GetMapping("/teachers")
    public String teachers(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        List<User> teachers = roleService.getAllTeachers();
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
            List<User> teachers = roleService.getAllTeachers();
            model.addAttribute("teachers", teachers);
            return "teacher_add_table";
        } else {
            Role teacherRole = roleService.getDepartmentById(2L);
            newTeacher.getRoles().add(teacherRole);
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


    @GetMapping("/teachers/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/teachers";
    }

    @GetMapping("/accountants/delete/{id}")
    public String deleteAccountant(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/accountants";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteUserById(id);
        return "redirect:/students";
    }


    // Accountant Page and Add
    @GetMapping("/accountants")
    public String accountants(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        model.addAttribute("accountants", roleService.getAllAccountants());
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
            model.addAttribute("accountants", roleService.getAllAccountants());
            return "financial_add_table";
        } else {
            Role accountantRole = roleService.getDepartmentById(3L);
            newAccountant.getRoles().add(accountantRole);
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
            BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            List<Student> students = studentService.getAllStudents();
            model.addAttribute("students", students);
            return "student_add_table";
        } else {
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

    @PostMapping("/bootcamps/addBootcamp")
    public String addBootcamp(
            @Valid @ModelAttribute("newBootcamp") Bootcamp newBootcamp,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            List<Bootcamp> bootcamps = bootcampService.getAllBootcamps();
            model.addAttribute("bootcamps", bootcamps);
            return "bootcamp_add_table";
        } else {
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
        List<Student> students = studentService.getAllStudents();
        List<Bootcamp> bootcamps = bootcampService.getAllBootcamps();
        List<Approval> approvals = approvalService.getAllApprovals();
        List<User> teachers = roleService.getAllTeachers();
        List<User> accountants = roleService.getAllAccountants();
        model.addAttribute("certificates", certificates);
        model.addAttribute("students", students);
        model.addAttribute("bootcamps", bootcamps);
        model.addAttribute("approvals", approvals);
        model.addAttribute("teachers", teachers);
        model.addAttribute("accountants", accountants);
        model.addAttribute("newCertificate", new Certificate());
        return "certificate_add_table";
    }


    @PostMapping("/certificates/addCertificate")
    public String addCertificate(
            @Valid @ModelAttribute("newCertificate") Certificate newCertificate,
            BindingResult bindingResult,
            @RequestParam("teacherId") Long teacherId,
            @RequestParam("accountantId") Long accountantId,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            List<Certificate> certificates = certificateService.getAllCertificates();
            model.addAttribute("certificates", certificates);
            return "certificate_add_table";
        } else {
            // Create and assign approvals
            Approval teacherApproval = new Approval();
            Approval accountantApproval = new Approval();

            // Find teacher and accountant
            User teacher = userService.findById(teacherId);
            User accountant = userService.findById(accountantId);

            // Save approvals and get the saved instances
            approvalService.addNewApprovalToCertificateForTeacher(teacherApproval, teacher);
            // No need to fetch it again

            approvalService.addNewApprovalToCertificateForFinancial(accountantApproval, accountant);
            // No need to fetch it again

            // Save certificate
            certificateService.addCertificate(newCertificate);

            // Assign approvals to the saved certificate
            approvalService.assignApprovalToCertificate(teacherApproval, newCertificate);
            approvalService.assignApprovalToCertificate(accountantApproval, newCertificate);

            return "redirect:/certificates";
        }
    }


    @GetMapping("/bootcamp/{id}")
    public String getBootcampDetails(@PathVariable("id") Long bootcampId, Model model) {
        Bootcamp bootcamp = bootcampService.getBootcamp(bootcampId);
        List<Student> students = bootcampService.getStudentsForBootcamp(bootcampId);

        model.addAttribute("bootcamp", bootcamp);
        model.addAttribute("students", students);

        return "bootcampDetails";  // Thymeleaf template name
    }
}


