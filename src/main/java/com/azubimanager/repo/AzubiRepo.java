package com.azubimanager.repo;

import com.azubimanager.model.Azubi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AzubiRepo extends JpaRepository<Azubi, Long> {

    Azubi findAzubiById(Long id);
}
