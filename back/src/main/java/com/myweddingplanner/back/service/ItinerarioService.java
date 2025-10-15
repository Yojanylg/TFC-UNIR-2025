package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.model.Itinerario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ItinerarioService {

    Optional<Itinerario> findById(Long id);

    List<Itinerario> findAll();

    Itinerario save(Itinerario itinerario);

    void deleteById (Long id);
}
