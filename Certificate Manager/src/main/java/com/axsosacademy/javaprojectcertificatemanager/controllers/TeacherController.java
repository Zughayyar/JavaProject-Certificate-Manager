package com.axsosacademy.javaprojectcertificatemanager.controllers;

import com.axsosacademy.javaprojectcertificatemanager.models.Approval;
import com.axsosacademy.javaprojectcertificatemanager.models.Bootcamp;
import com.axsosacademy.javaprojectcertificatemanager.models.User;
import com.axsosacademy.javaprojectcertificatemanager.services.ApprovalService;
import com.axsosacademy.javaprojectcertificatemanager.services.BootcampService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        // Check if the user is logged in, redirect if not
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
    
        // Get the logged-in user
        User loggedUser = (User) session.getAttribute("loggedUser");
    
        // Get all bootcamps and approvals
        List<Bootcamp> bootcamps = bootcampService.getAllBootcamps();
        List<Approval> approvals = approvalService.getAllApprovals();
    
        // Create a set to store the IDs of the bootcamps that the logged-in user is approved for
        Set<Long> approvedBootcampIds = new HashSet<>();
    
        // Iterate through the approvals to find those belonging to the logged-in user
// Iterate through the approvals to find those belonging to the logged-in user
for (Approval approval : approvals) {
    if (approval.getUser().getId().equals(loggedUser.getId())) {
        // Assuming `approval.getCertificate()` gives you the certificate related to the approval
        // If each certificate has associated bootcamps, and you need to add their IDs, you can loop through them.
        for (Bootcamp bootcamp : approval.getCertificate().getBootcamps()) {
            approvedBootcampIds.add(bootcamp.getId()); // Add each bootcamp's ID to the set
        }
    }
}

    
        // Now filter the list of bootcamps by checking if the bootcamp ID is in the approved set
        List<Bootcamp> availableBootcamps = bootcamps.stream()
                .filter(bootcamp -> approvedBootcampIds.contains(bootcamp.getId())) // Corrected: use bootcamp.getId()
                .collect(Collectors.toList());
    
        // Add the available bootcamps to the model
        model.addAttribute("availableBootcamps", availableBootcamps);
    
        // Return the teacher dashboard view
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
