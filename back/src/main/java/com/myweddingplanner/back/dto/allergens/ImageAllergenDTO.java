package com.myweddingplanner.back.dto.allergens;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageAllergenDTO {
    private Long idImage;
    private String link;
    private String imageType;
}
