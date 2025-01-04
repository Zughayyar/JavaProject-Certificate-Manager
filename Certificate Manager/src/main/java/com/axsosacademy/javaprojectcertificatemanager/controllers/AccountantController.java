package com.axsosacademy.javaprojectcertificatemanager.controllers;

import com.axsosacademy.javaprojectcertificatemanager.models.Bootcamp;
import com.axsosacademy.javaprojectcertificatemanager.services.BootcampService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;


@Controller
public class AccountantController {

    private final BootcampService bootcampService;
    public AccountantController(BootcampService bootcampService) {
        this.bootcampService = bootcampService;
    }


    @GetMapping("/accountantDashboard")
    public String bootcampDashboard(HttpSession session, Model model) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        List<Bootcamp> bootcamps = bootcampService.getAllBootcamps();
        Collections.reverse(bootcamps);
        model.addAttribute("bootcamps", bootcamps);
        return "accountantDashboard";
    }


    @GetMapping("/accountant/bootcamp/{id}")
    public String showCertificatesForBootcamp(@PathVariable("id") Long bootcampId, Model model) {
        Bootcamp bootcamp = bootcampService.getBootcamp(bootcampId);
        model.addAttribute("bootcamp", bootcamp);
        return "certificates_accountant";  // Thymeleaf template name
    }





}
