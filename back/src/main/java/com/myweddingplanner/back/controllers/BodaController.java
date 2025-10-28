package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.BodaDTO;
import com.myweddingplanner.back.service.BodaService;
import com.myweddingplanner.back.service.NovioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bodas")
@Tag(name = "API bodas",
        description = "CRUD bodas de la App")
public class BodaController {

    private final BodaService bodaService;
    private final NovioService novioService;

    public BodaController(BodaService bodaService, NovioService novioService) {
        this.bodaService = bodaService;
        this.novioService = novioService;
    }

    @GetMapping("error")
    public String getError(){
        return "Error en la api";
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<BodaDTO> getById(@PathVariable Long id){
        if (bodaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bodaService.findById(id).get());
    }

    // READ ONE por idUsuario
    @GetMapping(params = "idUsuario")
    public ResponseEntity<BodaDTO> buscarBodaActualPorUsuario(@RequestParam Long idUsuario){

        Optional<BodaDTO> boda = bodaService.buscarBodaActualPorUsuario(idUsuario);

        return bodaService.buscarBodaActualPorUsuario(idUsuario)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<BodaDTO>> getall(){
        return ResponseEntity.ok(bodaService.findAll());
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

    // CREATE
    @PostMapping
    public ResponseEntity<BodaDTO> create(@RequestBody BodaDTO dto){

        BodaDTO nuevo = bodaService.save(dto);


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
        if (bodaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        bodaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
