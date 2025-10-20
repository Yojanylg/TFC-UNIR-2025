package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.AlergenoDTO;
import com.myweddingplanner.back.dto.ItinerarioDTO;
import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.model.Itinerario;

import java.util.List;
import java.util.Optional;

public interface ItinerarioService {

    Optional<Itinerario> findById(Long id);

    List<Itinerario> findAll();

    Itinerario save(Itinerario itinerario);

    void deleteById(Long id);

}
