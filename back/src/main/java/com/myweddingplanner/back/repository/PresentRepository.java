package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Present;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PresentRepository extends JpaRepository<Present, Long> {

    List<Present> findByUserAppId(Long userId);
}
