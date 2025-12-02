package com.myweddingplanner.back.dto.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageProductDTO {

    private Long idImage;
    private String link;
    private String imageType;

}
