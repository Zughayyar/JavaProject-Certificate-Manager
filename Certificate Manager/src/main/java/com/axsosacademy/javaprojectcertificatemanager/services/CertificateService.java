package com.axsosacademy.javaprojectcertificatemanager.services;

import com.axsosacademy.javaprojectcertificatemanager.initializers.UniqueIdGenerator;
import com.axsosacademy.javaprojectcertificatemanager.models.Certificate;
import com.axsosacademy.javaprojectcertificatemanager.repositories.ApprovalRepository;
import com.axsosacademy.javaprojectcertificatemanager.repositories.CertificateRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CertificateService {
    private final CertificateRepository certificateRepository;
    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }
        @Autowired
    private ApprovalRepository approvalRepository;

    // Get All Certificates
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    // Add new Certificate
    public void addCertificate(Certificate certificate) {
        certificateRepository.save(certificate);
        List<Certificate> certificateList = certificateRepository.findAll();
        Certificate lastCertificate = certificateList.get(certificateList.size() - 1);
        Long lastCertificateId = lastCertificate.getId();
        Date lastCertificateDate = lastCertificate.getCreatedAt();
        String lastCertUniqueId = UniqueIdGenerator.generateUniqueId(lastCertificateId, lastCertificateDate);
        lastCertificate.setUniqueID(lastCertUniqueId);
        certificateRepository.save(lastCertificate);
    }
    @Transactional
    public void deleteCertificateById(Long id) {
        // Delete all approvals related to the certificate
        approvalRepository.deleteByCertificateId(id);

        // Delete the certificate
        certificateRepository.deleteById(id);
    }

}
