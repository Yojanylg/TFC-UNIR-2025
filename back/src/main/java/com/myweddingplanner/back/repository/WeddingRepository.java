package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeddingRepository extends JpaRepository<Wedding, Long> {

    Optional<Wedding> findById(Long id);
    Optional<Wedding> findByGroomsUserEmail(String email);
    Optional<Wedding> findByInvitationsUserEmail(String email);

}
