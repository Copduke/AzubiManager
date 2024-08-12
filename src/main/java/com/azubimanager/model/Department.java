package com.azubimanager.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @ManyToMany
    //@JoinTable(
    //        name = "jobtitle_department",
    //        joinColumns = @JoinColumn(name = "department_id"),
    //        inverseJoinColumns = @JoinColumn(name = "jobtitle_id"))
    Set<JobTitle> jobTitles;
}
