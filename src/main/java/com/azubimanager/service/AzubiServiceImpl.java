package com.azubimanager.service;

import com.azubimanager.model.Azubi;
import com.azubimanager.repo.AzubiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AzubiServiceImpl implements AzubiService{

    @Autowired
    private AzubiRepo azubiRepo;

    public Azubi addAzubi(Azubi azubi) {
        return azubiRepo.save(azubi);
    }

    public List<Azubi> findAllAzubis() {
        return azubiRepo.findAll();
    }

    public void deleteAzubiById(Long id) {
        azubiRepo.delete(azubiRepo.findAzubiById(id));
    }

    public Azubi findAzubiById(Long id) {
        return azubiRepo.findAzubiById(id);
    }

    public void saveAzubi(Azubi azubi) {
        azubiRepo.save(azubi);
    }

    public void updateAzubi(Azubi azubi) {
        azubiRepo.save(azubi);
    }
}
