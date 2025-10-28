package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Novio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NovioRepository extends JpaRepository<Novio, Long> {

    Optional<Novio> findByEmail(String email);

    @Query("SELECT DISTINCT n.boda.id FROM Novio n WHERE n.email = :email AND n.boda IS NOT NULL")
    List<Long> buscarIdBodasPorEmail(@Param("email") String email);

    boolean existsByEmail(String email);


}
