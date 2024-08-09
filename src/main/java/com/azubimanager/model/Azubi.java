package com.azubimanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Azubi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String department;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "job_title_id")
    private JobTitle jobTitle;
    private String imageUrl;

    public Azubi() {
    }

    public Azubi(Long id, String name, String email, String phone, String department, JobTitle jobTitle, String imageUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.jobTitle = jobTitle;
        this.imageUrl = imageUrl;
    }
}