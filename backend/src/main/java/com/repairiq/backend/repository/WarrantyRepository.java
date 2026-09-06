package com.repairiq.backend.repository;

import com.repairiq.backend.model.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
}