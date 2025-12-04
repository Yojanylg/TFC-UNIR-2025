package com.myweddingplanner.back.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    // Datos comunes a registro Novios e Invitados

    private String name;

    private String firstSurname;

    private String secondSurname;

    private String email;

    private String password;

    // Datos de registro novio

    private boolean isGroom;

    private String nameGroom;

    private String firstSurnameGroom;

    private String secondSurnameGroom;

    private String emailGroom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate weddingDate;

}
