package com.myweddingplanner.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MiInvitacionDTO {

    private Long idBoda;

    private Long idInvitacion;

    private String nombreNovios;

    private String lugarBoda;

    private String fechaBoda;

    private boolean confirmado;
}
