package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.wedding.ListEmailInvitation;
import com.myweddingplanner.back.dto.wedding.ListWeddingPresentDTO;
import com.myweddingplanner.back.dto.wedding.WeddingDTO;
import com.myweddingplanner.back.dto.wedding.WeddingPresentDTO;
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

        // Obtener Token
        String token = authorizationHeader.substring(7);
        // Obtener UserApp ID
        Long id = jwtService.extractUserId(token);

       Optional<WeddingDTO> optWedding = weddingService.getWeddingPreparingByUserId(id);

       if (optWedding.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(Map.of("error", "no existe boda"));
       }

        return ResponseEntity.ok(optWedding.get());

    }

    // Get One
    @GetMapping("/{idWedding}")
    public ResponseEntity<?> getWedding (@RequestHeader(name = "Authorization", required = true) String authorizationHeader,
                                         @PathVariable Long idWedding){

        return weddingService.getById(idWedding)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    // Get presents by id wedding
    @GetMapping("/presents/{idWedding}")
    public ResponseEntity<?> getWeddingPresents (@RequestHeader(name = "Authorization", required = true) String authorizationHeader,
                                         @PathVariable Long idWedding){

        return ResponseEntity.ok(weddingService.getListWeddingPresent(idWedding));

    }
    // Get invitations by id wedding
    @GetMapping("/invitations/{idWedding}")
    public ResponseEntity<?> getWeddingInvitations (@RequestHeader(name = "Authorization", required = true) String authorizationHeader,
                                                 @PathVariable Long idWedding){

        return ResponseEntity.ok(weddingService.getListWeddingInvitation(idWedding));

    }

    /* ----------------- CREATE DE USERS ----------------- */

    @PostMapping("/invitations/{idWedding}")
    public ResponseEntity<?> addInvitations (@RequestHeader(name = "Authorization", required = true) String authorizationHeader,
                                             @PathVariable Long idWedding,
                                             @RequestBody ListEmailInvitation listEmailInvitation){

        listEmailInvitation.setIdWedding(idWedding);

        return ResponseEntity.ok(weddingService.addInvitation(listEmailInvitation));
    }


    /* ----------------- UPDATE DE USERS ----------------- */

    @PutMapping("/{idWedding}")
    public ResponseEntity<?> updateWedding(@RequestHeader(name = "Authorization", required = true) String authorizationHeader,
                                    @PathVariable Long idWedding,
                                    @RequestBody WeddingDTO dto){

        dto.setIdWedding(idWedding);

        return ResponseEntity.ok(weddingService.updateWeddingDTO(dto));
    }

    @PutMapping("/presents/{idWedding}")
    public ResponseEntity<?> updateWeddingPresents(@RequestHeader(name = "Authorization", required = true) String authorizationHeader,
                                           @PathVariable Long idWedding,
                                           @RequestBody ListWeddingPresentDTO dto){

        dto.setIdWedding(idWedding);

        for (WeddingPresentDTO w : dto.getMyWeddingPresent()){
            System.out.println(w.isConfirm());

        }

        return ResponseEntity.ok(weddingService.updateListWeddingPresent(dto));
    }
}
