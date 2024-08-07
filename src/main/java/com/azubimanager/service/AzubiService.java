package com.azubimanager.service;

import com.azubimanager.model.Azubi;
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

    void updateAzubi(Azubi updatedAzubi);
}
