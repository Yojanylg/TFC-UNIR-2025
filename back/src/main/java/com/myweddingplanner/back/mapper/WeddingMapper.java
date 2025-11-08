package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.WeddingDTO;
import com.myweddingplanner.back.model.Wedding;


public interface WeddingMapper {

    WeddingDTO toWeddingDTO (Wedding wedding);


}
