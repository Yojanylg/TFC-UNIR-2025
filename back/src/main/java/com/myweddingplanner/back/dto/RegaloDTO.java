package com.myweddingplanner.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegaloDTO {

    private Long id;

    private Long idProducto;

    private String descripcion;

    private String enlace;

    private Long idUsuario;

    private double valor;

    private boolean confirmado;

}
