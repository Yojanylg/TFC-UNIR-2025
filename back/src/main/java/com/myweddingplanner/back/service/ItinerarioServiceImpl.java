package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Itinerario;
import com.myweddingplanner.back.repository.ItinerarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ItinerarioServiceImpl implements ItinerarioService{

    private final ItinerarioRepository repository;

    public ItinerarioServiceImpl(ItinerarioRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<Itinerario> findById(Long id) {
        return repository.findById(id);
    }



    @Override
    public List<Itinerario> findAll() {
        return repository.findAll();
    }

    @Override
    public Itinerario save(Itinerario itinerario) {
        return repository.save(itinerario);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

}
