package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.RegisterRequest;
import com.myweddingplanner.back.dto.RegisterResult;

public interface RegisterService {

    RegisterResult registerUserApp(RegisterRequest req);
}
