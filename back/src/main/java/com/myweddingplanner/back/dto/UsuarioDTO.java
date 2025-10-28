package com.myweddingplanner.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    private Long id;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String email;

    private String telefono;

    private List<UsuarioAlergenoDTO> alergias = new ArrayList<>();

}
