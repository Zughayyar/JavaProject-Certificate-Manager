package com.axsosacademy.javaprojectcertificatemanager.services;

import com.axsosacademy.javaprojectcertificatemanager.models.Bootcamp;
import com.axsosacademy.javaprojectcertificatemanager.repositories.BootcampRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BootcampService {
    private final BootcampRepository bootcampRepository;
    public BootcampService(BootcampRepository bootcampRepository) {
        this.bootcampRepository = bootcampRepository;
    }


    // Get All Bootcamps
    public List<Bootcamp> getAllBootcamps() {
        return bootcampRepository.findAll();
    }
}
