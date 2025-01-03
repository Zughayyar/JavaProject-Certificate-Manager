package com.axsosacademy.javaprojectcertificatemanager.services;

import com.axsosacademy.javaprojectcertificatemanager.models.Certificate;
import com.axsosacademy.javaprojectcertificatemanager.repositories.CertificateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateService {
    private final CertificateRepository certificateRepository;
    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    // Get All Certificates
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    // Add new Certificate
    public void addCertificate(Certificate certificate) {
        certificateRepository.save(certificate);
    }

}
