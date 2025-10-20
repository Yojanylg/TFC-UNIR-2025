package com.myweddingplanner.back.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NovioDTO{

private Long id;

private String nombre;


private String apellido1;


private String apellido2;

private String email;

private String telefono;

}
