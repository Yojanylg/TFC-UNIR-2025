package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.AlergenoDTO;
import com.myweddingplanner.back.dto.AlergenoRUDTO;
import com.myweddingplanner.back.dto.UsuarioDTO;
import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.service.AlergenoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alergenos")
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

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<AlergenoRUDTO> getById(@PathVariable Long id){

        Optional<Alergeno> alergenoOpt = alergenoService.findById(id);

        if (alergenoOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Alergeno alergeno = alergenoOpt.get();

        return ResponseEntity.ok(alergenoService.toRUDTO(alergeno));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<AlergenoRUDTO>> getAll(){

        List<AlergenoRUDTO> list = new ArrayList<>();

        for (Alergeno alergeno : alergenoService.findAll()){
            list.add(alergenoService.toRUDTO(alergeno));
        }

        return ResponseEntity.ok(list);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AlergenoRUDTO> update(@PathVariable Long id, @RequestBody AlergenoRUDTO dto){

        Optional<Alergeno> alergenoOpt = alergenoService.findById(id);

        if (alergenoOpt.isEmpty()) return ResponseEntity.notFound().build();

        if (dto.getId() != null && !dto.getId().equals(id)) return ResponseEntity.badRequest().build();

        Alergeno alergenoActualizado = alergenoService.mergeAndSave(alergenoOpt.get(), dto);

        return ResponseEntity.ok(alergenoService.toRUDTO(alergenoActualizado));
    }

}
