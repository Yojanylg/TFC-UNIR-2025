package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.UserWedding;

import java.util.List;
import java.util.Optional;

public interface UserWeddingService {

    Optional<UserWedding> findById(Long id);

    List<UserWedding> findByUserId(Long idUser);

    List<UserWedding> findAll();

    UserWedding save(UserWedding userWedding);

    void deleteById (Long id);

    Optional<UserWedding> findByUserEmail(String email);

    boolean existsByEmailGroom(String email);

    UserWedding findByEmailGroom(String email);
}
