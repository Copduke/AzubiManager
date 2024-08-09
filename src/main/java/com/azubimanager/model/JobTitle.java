package com.azubimanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class JobTitle {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @OneToMany
    private Set<Azubi> azubis;
}
