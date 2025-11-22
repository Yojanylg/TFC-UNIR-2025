package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.AllergiesUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllergiesRepository extends JpaRepository<AllergiesUser, Long> {

    List<AllergiesUser> findByUserId(Long id);
}
