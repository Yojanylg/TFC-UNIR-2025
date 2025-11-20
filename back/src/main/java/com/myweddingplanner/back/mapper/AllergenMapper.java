package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.allergens.AllergenDTO;
import com.myweddingplanner.back.dto.allergens.ImageAllergenDTO;
import com.myweddingplanner.back.model.Allergen;

public interface AllergenMapper {

    AllergenDTO toAllergenDTO (Allergen entity);

    ImageAllergenDTO toImageAllergenDTO(com.myweddingplanner.back.model.ImageAllergen entity);

}
