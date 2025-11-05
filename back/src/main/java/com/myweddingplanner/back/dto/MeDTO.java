package com.myweddingplanner.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeDTO {

    private Long userId;

    private UsuarioDTO usuario;

    private List<MiBodaDTO> misBodas;

    private List<MiInvitacionDTO> misInvitaciones;

}
