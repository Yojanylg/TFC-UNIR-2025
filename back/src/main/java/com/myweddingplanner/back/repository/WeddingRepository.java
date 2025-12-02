package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface WeddingRepository extends JpaRepository<Wedding, Long> {

    Optional<Wedding> findById(Long id);
    Optional<List<Wedding>> findByGroomsUserAppId(Long id);

}
