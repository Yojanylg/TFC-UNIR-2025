package com.myweddingplanner.back.dto;

import jakarta.persistence.Column;
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

    // No hay relacion con la tabla porque un invitado puede o no estar registrado
    @Column(name = "id_usuario")
    private Long idUsuario;

    private String nombre;

    @Column(name = "apellido_1")
    private String apellido1;

    @Column(name = "apellido_2")
    private String apellido2;

    private String email;

    private String telefono;

    private boolean confirmado;

    @Column(name = "acompanantes_menores")
    private int acomptesMenores;

    @Column(name = "acompanantes_mayores")
    private int acomptesMayores;

}
