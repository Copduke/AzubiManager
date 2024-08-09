package com.azubimanager.service;

import com.azubimanager.model.Azubi;
import com.azubimanager.model.JobTitle;
import com.azubimanager.repo.AzubiRepo;
import com.azubimanager.repo.JobTitleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AzubiServiceImpl implements AzubiService{

    @Autowired
    private AzubiRepo azubiRepo;
    @Autowired
    private JobTitleRepo jobTitleRepo;

    public List<Azubi> findAllAzubis() {
        return azubiRepo.findAll();
    }

    public void deleteAzubiById(Long id) {
        azubiRepo.delete(azubiRepo.findAzubiById(id));
    }

    public Azubi findAzubiById(Long id) {
        return azubiRepo.findAzubiById(id);
    }

    public List<JobTitle> findAllJobTitles() {
        return jobTitleRepo.findAll();
    }

    @Override
    public JobTitle findJobTitleById(Long id) {
        return jobTitleRepo.findJobTitleById(id);
    }

    public void saveAzubi(Azubi azubi) {
        azubiRepo.save(azubi);
    }
}
