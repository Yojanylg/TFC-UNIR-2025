package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Novio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NovioRepository extends JpaRepository<Novio, Long> {

    Optional<Novio> findByEmail(String email);
    boolean existsByEmail(String email);
}
