package com.axsosacademy.javaprojectcertificatemanager.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueID;

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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "certificates_students",
            joinColumns = @JoinColumn(name = "certificate_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "certificates_bootcamps",
            joinColumns = @JoinColumn(name = "certificate_id"),
            inverseJoinColumns = @JoinColumn(name = "bootcamp_id")
    )
    private List<Bootcamp> bootcamps;

    @OneToMany(mappedBy = "certificate", fetch = FetchType.LAZY)
    private List<Approval> approvalList;

    // Constructors

    public Certificate() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Bootcamp> getBootcamps() {
        return bootcamps;
    }

    public List<Approval> getApprovalList() {
        return approvalList;
    }

    // Setters

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setBootcamps(List<Bootcamp> bootcamps) {
        this.bootcamps = bootcamps;
    }

    public void setApprovalList(List<Approval> approvalList) {
        this.approvalList = approvalList;
    }

    // Get Over all Certificate Status
    public boolean getCertificateStatus(){
        for (Approval approval : approvalList) {
            if (!approval.isApprovalStatus()) {
                return false;
            }
        }
        return true;
    }
}
