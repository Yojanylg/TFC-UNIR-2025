package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Present;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresentRepository extends JpaRepository<Present, Long> {

    List<Present> findByUserAppId(Long userId);
}
