package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.UserApp;

import java.util.List;
import java.util.Optional;

public interface UserAppService {

    Optional<UserApp> findById(Long id);

    List<UserApp> findAll();

    UserApp save(UserApp entity);

    void deleteById (Long id);

    Optional<UserApp> findByEmail(String email);

    boolean existsByEmail(String email);
}
