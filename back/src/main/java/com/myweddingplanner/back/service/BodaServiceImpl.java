package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.BodaDTO;
import com.myweddingplanner.back.mappers.BodaMapper;
import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.model.Boda;
import com.myweddingplanner.back.repository.BodaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodaServiceImpl implements BodaService{

    private final BodaRepository repository;
    private final BodaMapper mapper;

    public BodaServiceImpl(BodaRepository repository, BodaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Optional<BodaDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Override
    public List<BodaDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public BodaDTO save(BodaDTO dto) {
        Boda entity = mapper.toEntity(dto);
        Boda salvado = repository.save(entity);
        return mapper.toDTO(salvado);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }
}
