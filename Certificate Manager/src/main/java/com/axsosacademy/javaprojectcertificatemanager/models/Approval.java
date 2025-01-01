package com.axsosacademy.javaprojectcertificatemanager.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "approvals")
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean approvalStatus;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    // Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User approvedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    // Constructor

    public Approval() {
    }

    public Approval(boolean approvalStatus, User user, Certificate certificate) {
        this.approvalStatus = approvalStatus;
        this.approvedBy = user;
        this.certificate = certificate;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public User getUser() {
        return approvedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    // Setters


    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void setUser(User user) {
        this.approvedBy = user;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }
}
