package com.axsosacademy.javaprojectcertificatemanager.services;


import com.axsosacademy.javaprojectcertificatemanager.models.Approval;
import com.axsosacademy.javaprojectcertificatemanager.models.Certificate;
import com.axsosacademy.javaprojectcertificatemanager.models.User;
import com.axsosacademy.javaprojectcertificatemanager.repositories.ApprovalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalService {
    private final ApprovalRepository approvalRepository;
    public ApprovalService(ApprovalRepository approvalRepository) {
        this.approvalRepository = approvalRepository;
    }

    // Get All Approvals
    public List<Approval> getAllApprovals() {
        return approvalRepository.findAll();
    }

    // Assign Teacher Approval to a Certificate
    public void addNewApprovalToCertificateForTeacher(Approval approval, User teacher) {
        approval.setUser(teacher);
        approvalRepository.save(approval);
    }

    // Assign Financial Approval to a Certificate
    public void addNewApprovalToCertificateForFinancial(Approval approval, User account) {
        approval.setUser(account);
        approvalRepository.save(approval);
    }

    // Assign Approval to Certificate
    public void assignApprovalToCertificate(Approval approval, Certificate certificate) {
        approval.setCertificate(certificate);
        approvalRepository.save(approval);
    }


}
