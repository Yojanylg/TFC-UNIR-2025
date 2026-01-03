package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.UserWedding;
import com.myweddingplanner.back.repository.UserWeddingRepository;
import org.springframework.stereotype.Service;

@Service
public class UserWeddingServiceImpl implements UserWeddingService{

    private final UserWeddingRepository userWeddingRepository;

    public UserWeddingServiceImpl(UserWeddingRepository userWeddingRepository) {
        this.userWeddingRepository = userWeddingRepository;
    }


    @Override
    public boolean existsByEmailGroom(String email) {
        return userWeddingRepository.existsByEmailGroom(email);
    }

    @Override
    public UserWedding findByEmailGroom(String email) {
        return userWeddingRepository.findByEmailGroom(email).orElseThrow();
    }
}
