package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.WeddingDTO;
import com.myweddingplanner.back.model.Wedding;
import org.springframework.stereotype.Component;

@Component
public class WeddingMapperImpl implements WeddingMapper{
    @Override
    public WeddingDTO toWeddingDTO(Wedding wedding) {
        return null;
    }
}
