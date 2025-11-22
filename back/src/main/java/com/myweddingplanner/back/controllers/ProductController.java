package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@Tag(name = "API productos",
        description = "CRUD productos de la App")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /* ----------------- GET DE USERS ----------------- */

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

}
