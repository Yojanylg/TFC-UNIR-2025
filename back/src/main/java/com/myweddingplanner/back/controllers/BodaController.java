package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.BodaDTO;
import com.myweddingplanner.back.service.BodaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bodas")
@Tag(name = "API Bodas",
        description = "CRUD bodas de la App")
public class BodaController {

    private final BodaService bodaService;

    public BodaController(BodaService bodaService) {
        this.bodaService = bodaService;
    }

    @GetMapping("error")
    public String getError(){
        return "Error en la api";
    }

    // CREATE
    @PostMapping
    public ResponseEntity<BodaDTO> create(@RequestBody BodaDTO dto){
        BodaDTO creado = bodaService.save(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<BodaDTO>> getAll(){
        return ResponseEntity.ok(bodaService.findAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<BodaDTO> getById(@PathVariable Long id){
        return bodaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<BodaDTO> update(@PathVariable Long id, @RequestBody BodaDTO dto){
        return bodaService.findById(id)
                .map(existing -> {
                    dto.setId(id);
                    BodaDTO actualizado = bodaService.save(dto);
                    return ResponseEntity.ok(actualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return bodaService.findById(id)
                .map(existing -> {
                    bodaService.deleteById(id);
                    return ResponseEntity.noContent().build();})
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
