package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.wedding.WeddingDTO;
import com.myweddingplanner.back.security.JwtService;
import com.myweddingplanner.back.service.WeddingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/weddings")
@Tag(name = "API bodas",
        description = "CRUD bodas de la App")
public class WeddingController {

    private final WeddingService weddingService;
    private final JwtService jwtService;

    public WeddingController(WeddingService weddingService, JwtService jwtService) {
        this.weddingService = weddingService;
        this.jwtService = jwtService;
    }


    /* ----------------- GET DE USERS ----------------- */

    // Get One -> preparing
    @GetMapping("/preparing")
    public ResponseEntity<?> getWeddingPreparing (@RequestHeader(name = "Authorization", required = true) String authorizationHeader){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }

        // Obtener Token
        String token = authorizationHeader.substring(7);
        // Obtener UserApp ID
        Long id = jwtService.extractUserId(token);

       Optional<WeddingDTO> optWedding = weddingService.findWeddingPreparingByUserId(id);


       if (optWedding.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(Map.of("error", "no existe boda"));
       }

        return ResponseEntity.ok(optWedding.get());

    }

    // Get One
    @GetMapping("/{id}")
    public ResponseEntity<?> getWedding (@RequestHeader(name = "Authorization", required = true) String authorizationHeader,
                                         @PathVariable Long id){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }

        return ResponseEntity.ok(weddingService.findById(id));

    }

    // Create


    // Update



}
