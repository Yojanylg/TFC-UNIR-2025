package com.myweddingplanner.back.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailYaRegistradoException extends RuntimeException{

    public EmailYaRegistradoException(String email){
        super("El email ya está registrado" + email);
    }
}
