package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Companion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanionRepository extends JpaRepository<Companion, Long> {
}
