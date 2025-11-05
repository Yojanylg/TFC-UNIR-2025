package com.myweddingplanner.back.dto;

import com.myweddingplanner.back.model.enums.EstadoBoda;
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
public class BodaDTO {

    private Long id;

    private String lugar;

    private String fecha;

    private EstadoBoda estado;

    private ItinerarioDTO itinerario;

    private List<NovioDTO> novios = new ArrayList<>();

    private List<InvitadoDTO> invitados = new ArrayList<>();

    private List<RegaloDTO> regalos = new ArrayList<>();
}
