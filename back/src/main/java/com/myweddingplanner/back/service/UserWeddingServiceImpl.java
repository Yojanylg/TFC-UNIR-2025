package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.UserWedding;
import com.myweddingplanner.back.repository.UserWeddingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserWeddingServiceImpl implements UserWeddingService{

    private final UserWeddingRepository userWeddingRepository;

    public UserWeddingServiceImpl(UserWeddingRepository userWeddingRepository) {
        this.userWeddingRepository = userWeddingRepository;
    }

    @Override
    public Optional<UserWedding> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserWedding> findByUserId(Long idUser) {
        return List.of();
    }

    @Override
    public List<UserWedding> findAll() {
        return List.of();
    }

    @Override
    public UserWedding save(UserWedding userWedding) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<UserWedding> findByUserEmail(String email) {
        return Optional.empty();
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
