package com.azubimanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Department {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToMany(cascade=CascadeType.ALL)
    private List<Azubi> azubis;
}
