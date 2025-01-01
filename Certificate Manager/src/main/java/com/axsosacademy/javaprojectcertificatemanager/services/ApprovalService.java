package com.axsosacademy.javaprojectcertificatemanager.services;


import com.axsosacademy.javaprojectcertificatemanager.models.Approval;
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


}
