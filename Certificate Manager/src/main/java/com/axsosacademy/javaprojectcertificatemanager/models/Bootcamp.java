package com.axsosacademy.javaprojectcertificatemanager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bootcamps")
public class Bootcamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First Name is required!")
    @Size(min = 3, max = 30, message = "First Name must be between 3 and 30 characters")
    private String bootcampName;

    @NotEmpty(message = "Description Name is required!")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

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
            name = "certificates_bootcamps",
            joinColumns = @JoinColumn(name = "bootcamp_id"),
            inverseJoinColumns = @JoinColumn(name = "certificate_id")
    )
    private List<Certificate> certificates;

    // Constructor

    public Bootcamp() {
    }

    public Bootcamp(String bootcampName, String description, Date startDate, Date endDate) {
        this.bootcampName = bootcampName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    // Getters
    public Long getId() {
        return id;
    }

    public String getBootcampName() {
        return bootcampName;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    // Setters

    public void setBootcampName(String bootcampName) {
        this.bootcampName = bootcampName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
}
