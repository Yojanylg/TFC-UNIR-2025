package com.myweddingplanner.back.dto.users;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAllergies {

    private Long idUserAllergies;

    private Long idUser;

    private boolean gluten;

    private boolean crustaceos;

    private boolean huevos;

    private boolean pescado;

    private boolean cacahuete;

    private boolean soja;

    private boolean leche;

    private boolean frutosCascara;

    private boolean apio;

    private boolean mostaza;

    private boolean sesamo;

    private boolean sulfitos;

    private boolean altramuces;

    private boolean moluscos;

}
