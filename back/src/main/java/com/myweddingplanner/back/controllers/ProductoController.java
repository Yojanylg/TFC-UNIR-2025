package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.ProductoDTO;
import com.myweddingplanner.back.service.ProductoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "API Productos",
        description = "CRUD productos de la App")
public class ProductoController {

    private final ProductoService productoService;


    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("error")
    public String getError(){
        return "Error en la api";
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getall(){
        return ResponseEntity.ok(productoService.findAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getById(@PathVariable Long id){
        if (productoService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoService.findById(id).get());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO dto){

        return productoService.findById(id)
                .map(existing -> {
                    dto.setId(id);
                    ProductoDTO actualizado = productoService.save(dto);
                    return ResponseEntity.ok(actualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO dto){

        ProductoDTO nuevo = productoService.save(dto);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo.getId())
                .toUri();

        return ResponseEntity.created(location).body(nuevo);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (productoService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
