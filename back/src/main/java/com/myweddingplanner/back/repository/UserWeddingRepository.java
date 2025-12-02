package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.UserWedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserWeddingRepository extends JpaRepository<UserWedding, Long> {

    Optional<UserWedding> findByEmailGroom(String email);
    boolean existsByEmailGroom(String email);

}
