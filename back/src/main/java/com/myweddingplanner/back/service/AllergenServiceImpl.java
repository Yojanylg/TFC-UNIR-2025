package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.AllergenDTO;
import com.myweddingplanner.back.mapper.AllergenMapper;
import com.myweddingplanner.back.model.Allergen;
import com.myweddingplanner.back.repository.AllergenRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AllergenServiceImpl implements AllergenService{

    private final AllergenRepository allergenRepository;
    private final AllergenMapper allergenMapper;


    public AllergenServiceImpl(AllergenRepository allergenRepository, AllergenMapper allergenMapper) {
        this.allergenRepository = allergenRepository;
        this.allergenMapper = allergenMapper;
    }

    @Override
    public Optional<Allergen> findById(Long id) {
        return allergenRepository.findById(id);
    }

    @Override
    public List<AllergenDTO> findAll() {

        List<AllergenDTO> list = new ArrayList<>();

        for (Allergen a : allergenRepository.findAll()){
            list.add(allergenMapper.toAllergenDTO(a));
        }
        return list;
    }

    @Override
    public Allergen save(Allergen allergen) {
        return allergenRepository.save(allergen);
    }

    @Override
    public void deleteById(Long id) {
        allergenRepository.deleteById(id);
    }
}
