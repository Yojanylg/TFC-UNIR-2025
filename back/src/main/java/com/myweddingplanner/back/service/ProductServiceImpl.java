package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.products.ProductDTO;
import com.myweddingplanner.back.mapper.ProductMapper;
import com.myweddingplanner.back.model.Product;
import com.myweddingplanner.back.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> getAll() {

        List<ProductDTO> list = new ArrayList<>();

        for (Product p : productRepository.findAll()){
            list.add(productMapper.toProductDTO(p));
        }

        return list;
    }
}
