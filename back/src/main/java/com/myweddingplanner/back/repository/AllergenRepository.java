package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergenRepository extends JpaRepository<Allergen, Long> {
}
