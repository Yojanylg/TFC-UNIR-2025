package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Regalo;

import java.util.List;
import java.util.Optional;

public interface RegaloService {

    Optional<Regalo> findById(Long id);

    List<Regalo> findAll();

    Regalo save(Regalo regalo);

    void deleteById (Long id);
}
