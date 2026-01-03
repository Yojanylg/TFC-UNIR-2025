package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.products.ListProductDTO;
import com.myweddingplanner.back.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(
        name = "Productos",
        description = "Controlador encargado de suministrar a los novios el catálogo de productos " +
                "que pueden agregar a la lista de regalos de su boda.")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ----------------------------------------------------
    // -------------------- PRODUCTS ----------------------
    // ----------------------------------------------------
    @Operation(
            summary = "Obtener catálogo de productos",
            description = "Devuelve listado completo de productos disponibles en el catálogo " +
                    "para que los usuarios novios puedan seleccionar y agregarlos a su lista de" +
                    " regalos de boda"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Catalogo de productos obtenidos correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ListProductDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productService.getList());
    }
}
