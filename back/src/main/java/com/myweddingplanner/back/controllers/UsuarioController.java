package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.UsuarioDTO;
import com.myweddingplanner.back.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "API usuarios",
        description = "CRUD usuarios de la App")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping("error")
    public String getError(){
        return "Error en la api";
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getall(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id){
        if (usuarioService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioService.findById(id).get());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO dto){

        return usuarioService.findById(id)
                .map(existing -> {
                    dto.setId(id);
                    UsuarioDTO actualizado = usuarioService.save(dto);
                    return ResponseEntity.ok(actualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO dto){

        UsuarioDTO nuevo = usuarioService.save(dto);


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
        if (usuarioService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
