package com.azubimanager.service;

import com.azubimanager.model.Azubi;
import com.azubimanager.model.Department;
import com.azubimanager.model.JobTitle;
import com.azubimanager.repo.AzubiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AzubiService {

    Azubi addAzubi(Azubi azubi);

    List<Azubi> findAllAzubis();

    void saveAzubi(Azubi azubi);

    void deleteAzubiById(Long id);

    Azubi findAzubiById(Long id);

    List<JobTitle> findAllJobTitles();

    JobTitle findJobTitleById(Long id);

    Department findDepartmentById(Long id);
}
