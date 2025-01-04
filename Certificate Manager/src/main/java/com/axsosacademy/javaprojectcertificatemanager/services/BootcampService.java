package com.axsosacademy.javaprojectcertificatemanager.services;

import com.axsosacademy.javaprojectcertificatemanager.models.Bootcamp;
import com.axsosacademy.javaprojectcertificatemanager.models.Student;
import com.axsosacademy.javaprojectcertificatemanager.repositories.BootcampRepository;
import com.axsosacademy.javaprojectcertificatemanager.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BootcampService {
    private final BootcampRepository bootcampRepository;
    public BootcampService(BootcampRepository bootcampRepository) {
        this.bootcampRepository = bootcampRepository;
    }
      @Autowired
    private StudentRepository studentRepository;

    // Get All Bootcamps
    public List<Bootcamp> getAllBootcamps() {
        return bootcampRepository.findAll();
    }

    // Add New Bootcamp
    public void addBootcamp(Bootcamp bootcamp) {
        bootcampRepository.save(bootcamp);
    }

    public Bootcamp getBootcamp(Long id) {
        return bootcampRepository.findById(id).orElseThrow(() -> new RuntimeException("Bootcamp not found"));
    }

    public List<Student> getStudentsForBootcamp(Long bootcampId) {
        return studentRepository.findByCertificates_Bootcamps_Id(bootcampId);  // Correct query method
    }
}
