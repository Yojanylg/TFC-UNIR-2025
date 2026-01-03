package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.UserWedding;



public interface UserWeddingService {

    boolean existsByEmailGroom(String email);

    UserWedding findByEmailGroom(String email);
}
