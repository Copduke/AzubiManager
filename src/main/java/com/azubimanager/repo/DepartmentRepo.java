package com.azubimanager.repo;

import com.azubimanager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

    Department findDepartmentById(Long id);
}
