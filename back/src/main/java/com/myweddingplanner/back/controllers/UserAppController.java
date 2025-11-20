package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.users.MyUserAllergiesDTO;
import com.myweddingplanner.back.dto.users.MyUserDTO;
import com.myweddingplanner.back.dto.users.MyUserPresentDTO;
import com.myweddingplanner.back.security.JwtService;
import com.myweddingplanner.back.service.UserAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Tag(name = "API usuarios",
        description = "CRUD usuarios de la App")
public class UserAppController {

    private final UserAppService userAppService;
    private final JwtService jwtService;

    public UserAppController(UserAppService userAppService, JwtService jwtService) {
        this.userAppService = userAppService;
        this.jwtService = jwtService;
    }


    /* ----------------- GET DE USERS ----------------- */

    @GetMapping("/me")
    public ResponseEntity<?> getMyUserDTO (@RequestHeader (name = "Authorization", required = true) String authorizationHeader){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }

        // Obtener Token
        String token = authorizationHeader.substring(7);

        MyUserDTO dto = userAppService.findMyUserDTOById(jwtService.extractUserId(token));

        return ResponseEntity.ok(dto);

    }

    @GetMapping("/myAllergies")
    public ResponseEntity<?> getMyAllergies (@RequestHeader (name = "Authorization", required = true) String authorizationHeader){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }

        // Obtener Token
        String token = authorizationHeader.substring(7);

        // TODO
        MyUserAllergiesDTO dto = userAppService.findMyUserAllergiesDTOById(jwtService.extractUserId(token));

        return ResponseEntity.ok(dto);

    }

    @GetMapping("/myPresents")
    public ResponseEntity<?> getMyPresents (@RequestHeader (name = "Authorization", required = true) String authorizationHeader){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }

        // Obtener Token
        String token = authorizationHeader.substring(7);

        // TODO
        MyUserPresentDTO dto = userAppService.finMyUserPresentDTOById(jwtService.extractUserId(token));

        return ResponseEntity.ok(dto);

    }

}
