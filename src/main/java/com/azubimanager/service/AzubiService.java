package com.azubimanager.service;

import com.azubimanager.model.Azubi;
import com.azubimanager.model.Department;
import com.azubimanager.model.JobTitle;

import java.util.List;

public interface AzubiService {

    List<Azubi> findAllAzubis();

    void saveAzubi(Azubi azubi);

    void deleteAzubiById(Long id);

    Azubi findAzubiById(Long id);

    List<JobTitle> findAllJobTitles();

    JobTitle findJobTitleById(Long id);

    Department findDepartmentById(Long id);

    List<Department> findAllDepartments();
}
