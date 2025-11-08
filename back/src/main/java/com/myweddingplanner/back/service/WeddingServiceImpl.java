package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.WeddingDTO;
import com.myweddingplanner.back.mapper.WeddingMapper;
import com.myweddingplanner.back.model.Wedding;
import com.myweddingplanner.back.repository.WeddingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeddingServiceImpl implements WeddingService{

    private final WeddingRepository weddingRepository;
    private final WeddingMapper weddingMapper;

    public WeddingServiceImpl(WeddingRepository weddingRepository, WeddingMapper weddingMapper) {
        this.weddingRepository = weddingRepository;
        this.weddingMapper = weddingMapper;
    }

    @Override
    public Optional<WeddingDTO> findById(Long id) {

        Wedding wedding = weddingRepository.findById(id).orElseThrow();

        WeddingDTO dto = weddingMapper.toWeddingDTO(wedding);

        return Optional.of(dto);
    }

    @Override
    public List<WeddingDTO> findAll() {

        List<WeddingDTO> list = new ArrayList<>();

        for (Wedding w : weddingRepository.findAll()){
            list.add(weddingMapper.toWeddingDTO(w));
        }

        return list;
    }

    @Override
    public Wedding save(Wedding wedding) {
        return weddingRepository.save(wedding);
    }

    @Override
    public void deleteById(Long id) {

    }
}
