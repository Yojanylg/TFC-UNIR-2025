package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.AllergenDTO;
import com.myweddingplanner.back.dto.ImageAllergenDTO;
import com.myweddingplanner.back.model.Allergen;
import com.myweddingplanner.back.model.ImageAllergen;
import org.springframework.stereotype.Component;

@Component
public class AllergenMapperImpl implements AllergenMapper{
    @Override
    public AllergenDTO toAllergenDTO(Allergen entity) {

        AllergenDTO dto = new AllergenDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getId());

        for (ImageAllergen img : entity.getImages()){
            dto.getImages().add(toImageAllergenDTO(img));
        }

        return dto;
    }

    @Override
    public ImageAllergenDTO toImageAllergenDTO(ImageAllergen entity) {

        ImageAllergenDTO dto = new ImageAllergenDTO();
        dto.setId(entity.getId());
        dto.setLink(entity.getLink());
        dto.setImageType(entity.getImageType());
        dto.setAllergenId(entity.getAllergen().getId());

        return dto;
    }
}
