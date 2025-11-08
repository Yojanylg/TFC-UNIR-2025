package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.RegisterRequest;
import com.myweddingplanner.back.dto.RegistroResult;

public interface RegisterService {

    RegistroResult registerUserApp(RegisterRequest req);
}
