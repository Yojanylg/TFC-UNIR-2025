package com.myweddingplanner.back.mappers;

import com.myweddingplanner.back.dto.AlergenoDTO;
import com.myweddingplanner.back.model.Alergeno;

public final class AlergenoMapper {

    private AlergenoMapper(){}

    public static AlergenoDTO toDTO(Alergeno alergeno){

        if (alergeno == null) return null;

        AlergenoDTO dto = new AlergenoDTO();

        dto.setId(alergeno.getId());
        dto.setNombre(alergeno.getNombre());

        return dto;
    }

    public static Alergeno toEntity(AlergenoDTO dto){
        if (dto == null) return null;

        Alergeno alergeno = new Alergeno();

        alergeno.setId(dto.getId());
        alergeno.setNombre(dto.getNombre());

        return alergeno;
    }

}
