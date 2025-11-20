package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.allergens.AllergenDTO;
import com.myweddingplanner.back.dto.allergens.ImageAllergenDTO;
import com.myweddingplanner.back.model.Allergen;
import org.springframework.stereotype.Component;

@Component
public class AllergenMapperImpl implements AllergenMapper{
    @Override
    public AllergenDTO toAllergenDTO(Allergen entity) {

        AllergenDTO dto = new AllergenDTO();

        dto.setIdAllergen(entity.getId());
        dto.setName(entity.getId());

        for (com.myweddingplanner.back.model.ImageAllergen img : entity.getImages()){
            dto.getImages().add(toImageAllergenDTO(img));
        }

        return dto;
    }

    @Override
    public ImageAllergenDTO toImageAllergenDTO(com.myweddingplanner.back.model.ImageAllergen entity) {

        ImageAllergenDTO dto = new ImageAllergenDTO();
        dto.setIdImage(entity.getId());
        dto.setLink(entity.getLink());
        dto.setImageType(entity.getImageType());


        return dto;
    }
}
