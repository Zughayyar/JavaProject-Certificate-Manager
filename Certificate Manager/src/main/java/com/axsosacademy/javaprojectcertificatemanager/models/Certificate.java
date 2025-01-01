package com.axsosacademy.javaprojectcertificatemanager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    private boolean isTeacherApproved;
    private boolean isAccountApproved;

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

    public boolean isTeacherApproved() {
        return isTeacherApproved;
    }

    public boolean isAccountApproved() {
        return isAccountApproved;
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

    // Setters

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setTeacherApproved(boolean teacherApproved) {
        isTeacherApproved = teacherApproved;
    }

    public void setAccountApproved(boolean accountApproved) {
        isAccountApproved = accountApproved;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setBootcamps(List<Bootcamp> bootcamps) {
        this.bootcamps = bootcamps;
    }
}
