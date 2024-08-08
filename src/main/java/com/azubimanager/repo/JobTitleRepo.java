package com.azubimanager.repo;

import com.azubimanager.model.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTitleRepo extends JpaRepository<JobTitle, Long> {

    JobTitle findJobTitleById(Long id);
}
