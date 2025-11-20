package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.products.ImageProductDTO;
import com.myweddingplanner.back.dto.products.ProductDTO;
import com.myweddingplanner.back.model.ImageProduct;
import com.myweddingplanner.back.model.Product;

public interface ProductMapper {

    ProductDTO toProductDTO(Product entity);
    ImageProductDTO toImageProductDTO(ImageProduct entity);
}
