package com.myweddingplanner.back.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    // Datos comunes a registro Novios e Invitados

    private String nombre;

    private String firstSurname;

    private String secondSurname;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;


    // Datos de registro novio

    private boolean isGroom;

    private String nameGroom;

    private String firstSurnameGroom;

    private String secondSurnameGroom;

    private String emailGroom;

    private String weddingDate;

}
