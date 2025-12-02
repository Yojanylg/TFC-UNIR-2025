package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.products.ListProductDTO;
import com.myweddingplanner.back.dto.products.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAll();
    ListProductDTO getList();
}
