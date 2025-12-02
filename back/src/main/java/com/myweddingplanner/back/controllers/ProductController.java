package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.products.ListProductDTO;
import com.myweddingplanner.back.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Productos",
        description = "Controlador encargado de suministrar a los novios el catálogo de productos " +
                "que pueden agregar a la lista de regalos de su boda.")
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
        ListProductDTO dto = productService.getList();
        return ResponseEntity.ok(dto);
    }

}
