package com.axsosacademy.javaprojectcertificatemanager.controllers;

import com.axsosacademy.javaprojectcertificatemanager.models.Approval;
import com.axsosacademy.javaprojectcertificatemanager.models.Bootcamp;
import com.axsosacademy.javaprojectcertificatemanager.services.ApprovalService;
import com.axsosacademy.javaprojectcertificatemanager.services.BootcampService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class TeacherController {

    private final BootcampService bootcampService;
    private final ApprovalService approvalService;
    public TeacherController(BootcampService bootcampService, ApprovalService approvalService) {
        this.bootcampService = bootcampService;
        this.approvalService = approvalService;
    }

    @GetMapping("/teacherDashboard")
    public String teacherDashboard(HttpSession session, Model model) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        List<Bootcamp> bootcamps = bootcampService.getAllBootcamps();
        Collections.reverse(bootcamps);
        model.addAttribute("bootcamps", bootcamps);
        return "teacherDashboard";
    }

    @PostMapping("/teacher/bootcamp/{bootcampId}/certificate/update")
    public String updateApprovalStatus(
            @PathVariable("bootcampId") Long bootcampId,
            @RequestParam("approvalId") Long approvalId,
            @RequestParam(value="status", defaultValue="false") boolean status
    ) {

        Approval approval = approvalService.findById(approvalId);
        if (approval != null) {
            approval.setApprovalStatus(status);
            approvalService.save(approval);
        }

        return "redirect:/teacher/bootcamp/{bootcampId}";
    }


    @GetMapping("/teacher/bootcamp/{id}")
    public String showCertificatesForBootcamp(@PathVariable("id") Long bootcampId, Model model) {
        Bootcamp bootcamp = bootcampService.getBootcamp(bootcampId);
        model.addAttribute("bootcamp", bootcamp);
        return "certificates_teacher";  // Thymeleaf template name
    }


}
