package com.myweddingplanner.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvitadoDTO {

    private Long id;

    private Long idUsuario;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String email;

    private String telefono;

    private boolean confirmado;

    private int acomptesMenores;

    private int acomptesMayores;

}
