package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Boda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BodaRepository extends JpaRepository<Boda, Long> {

    List<Boda> findByNoviosEmail(String email);

    List<Boda> findByInvitadosEmail(String email);


}
