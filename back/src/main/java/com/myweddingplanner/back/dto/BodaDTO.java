package com.myweddingplanner.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodaDTO {

    private Long id;

    private String lugar;

    private String fecha;

    private ItinerarioDTO itinerario;

    private List<RegaloBodaDTO> regalosBoda;

    private List<InvitacionUsuarioDTO> invitados;

    private List<BodaUsuarioDTO> novios;

}
