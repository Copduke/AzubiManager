package com.azubimanager.service;

import com.azubimanager.model.Azubi;
import com.azubimanager.repo.AzubiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AzubiService {

    private final AzubiRepo azubiRepo;

    @Autowired
    public AzubiService(AzubiRepo azubiRepo) {
        this.azubiRepo = azubiRepo;
    }

    public Azubi addAzubi(Azubi azubi) {
        return azubiRepo.save(azubi);
    }

    public List<Azubi> findAllAzubis() {
        return azubiRepo.findAll();
    }

    public void deleteAzubi(Long id) {
        azubiRepo.deleteAzubiById(id);
    }

    public Azubi findAzubiById(Long id) {
        return azubiRepo.findAzubiById(id);
    }
}
