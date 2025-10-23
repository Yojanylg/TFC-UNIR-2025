package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.AlergenoDTO;
import com.myweddingplanner.back.service.AlergenoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/auth/alergenos")
@Tag(name = "API Alergenos",
        description = "CRUD alergenos de la App")
public class AlergenoController {

    private final AlergenoService alergenoService;


    public AlergenoController(AlergenoService alergenoService) {
        this.alergenoService = alergenoService;
    }

    @GetMapping("error")
    public String getError(){
        return "Error en la api";
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<AlergenoDTO>> getall(){
        return ResponseEntity.ok(alergenoService.findAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<AlergenoDTO> getById(@PathVariable Long id){
        if (alergenoService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alergenoService.findById(id).get());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AlergenoDTO> update(@PathVariable Long id, @RequestBody AlergenoDTO dto){

        return alergenoService.findById(id)
                .map(existing -> {
                    dto.setId(id);
                    AlergenoDTO actualizado = alergenoService.save(dto);
                    return ResponseEntity.ok(actualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<AlergenoDTO> create(@RequestBody AlergenoDTO dto){

        AlergenoDTO nuevo = alergenoService.save(dto);


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
        if (alergenoService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        alergenoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
