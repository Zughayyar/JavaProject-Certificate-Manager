package com.axsosacademy.javaprojectcertificatemanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {


    @GetMapping("/accountantDashboard")
    public String bootcampDashboard() {
        return "accountantDashboard";
    }

    @GetMapping("teacherDashboard")
    public String teacherDashboard() {
        return "teacherDashboard";
    }




}
