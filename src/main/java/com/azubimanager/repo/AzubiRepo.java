package com.azubimanager.repo;

import com.azubimanager.model.Azubi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AzubiRepo extends JpaRepository<Azubi, Long> {

    void deleteAzubiById(Long id);

    Azubi findAzubiById(Long id);
}
