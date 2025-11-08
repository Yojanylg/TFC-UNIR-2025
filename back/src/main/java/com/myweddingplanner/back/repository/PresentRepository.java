package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Present;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentRepository extends JpaRepository<Present, Long> {
}
