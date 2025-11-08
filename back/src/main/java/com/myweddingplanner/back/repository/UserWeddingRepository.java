package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.UserWedding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserWeddingRepository extends JpaRepository<UserWedding, Long> {

    Optional<UserWedding> findByEmailGroom(String email);
    boolean existsByEmailGroom(String email);

    List<UserWedding> findByUserEmail(String email);
}
