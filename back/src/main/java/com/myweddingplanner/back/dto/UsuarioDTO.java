package com.myweddingplanner.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String email;

    private String telefono;

}
