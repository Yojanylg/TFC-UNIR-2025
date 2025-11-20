package com.myweddingplanner.back.dto.allergens;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllergenDTO {

    private Long idAllergen;
    private Long name;
    private List<ImageAllergenDTO> images = new ArrayList<>();
}
