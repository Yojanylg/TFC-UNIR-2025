package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.UserApp;
import com.myweddingplanner.back.repository.UserAppRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAppServiceImpl implements UserAppService{

    private final UserAppRepository userRepository;

    public UserAppServiceImpl(UserAppRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserApp> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserApp> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserApp save(UserApp entity) {
        return userRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<UserApp> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
