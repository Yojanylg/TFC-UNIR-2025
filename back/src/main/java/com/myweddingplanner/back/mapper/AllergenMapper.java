package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.AllergenDTO;
import com.myweddingplanner.back.dto.ImageAllergenDTO;
import com.myweddingplanner.back.model.Allergen;
import com.myweddingplanner.back.model.ImageAllergen;

public interface AllergenMapper {

    AllergenDTO toAllergenDTO (Allergen entity);

    ImageAllergenDTO toImageAllergenDTO(ImageAllergen entity);

}
