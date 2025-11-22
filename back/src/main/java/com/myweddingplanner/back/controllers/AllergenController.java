package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.service.AllergenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/allergens")
@Tag(name = "API alergicos",
        description = "CRUD alergicos de la App")
public class AllergenController {

    private final AllergenService allergenService;

    public AllergenController(AllergenService allergenService) {
        this.allergenService = allergenService;
    }

    /* ----------------- GET DE USERS ----------------- */

    @GetMapping
    public ResponseEntity<?> getAll(){

        return ResponseEntity.ok(allergenService.findAll());
    }
}
