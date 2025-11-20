package com.myweddingplanner.back.dto.products;

import com.myweddingplanner.back.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long idProduct;
    private String name;
    private String description;
    private String link;
    private double price;
    private ProductType type;
    private LocalDateTime startDate;
    private LocalDateTime updateDate;
    private List<ImageProductDTO> images;


}
