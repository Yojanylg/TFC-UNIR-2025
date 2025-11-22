package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.users.MyUserAllergiesDTO;
import com.myweddingplanner.back.dto.users.MyUserDTO;
import com.myweddingplanner.back.dto.users.ListUserPresentDTO;
import com.myweddingplanner.back.security.JwtService;
import com.myweddingplanner.back.service.UserAppService;
import com.myweddingplanner.back.service.UserInvitationWeddingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Tag(name = "API usuarios",
        description = "CRUD usuarios de la App")
public class UserAppController {

    private final UserAppService userAppService;
    private final UserInvitationWeddingService userInvitationWeddingService;
    private final JwtService jwtService;

    public UserAppController(UserAppService userAppService, UserInvitationWeddingService userInvitationWeddingService, JwtService jwtService) {
        this.userAppService = userAppService;
        this.userInvitationWeddingService = userInvitationWeddingService;
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

        ListUserPresentDTO dto = userAppService.finMyUserPresentDTOById(jwtService.extractUserId(token));

        return ResponseEntity.ok(dto);

    }

    @GetMapping("/myInvitations")
    public ResponseEntity<?> getMyInvitations (@RequestHeader (name = "Authorization", required = true) String authorizationHeader){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }

        // Obtener Token
        String token = authorizationHeader.substring(7);

        return ResponseEntity.ok(userInvitationWeddingService.getMyListInvitations(jwtService.extractUserId(token)));

    }



    /* ----------------- UPDATE DE USERS ----------------- */

    @PutMapping("/myAllergies")
    public ResponseEntity<?> updateMyAllergies (@RequestHeader(name = "Authorization", required = true) String authorizationHeader,
                                                @RequestBody MyUserAllergiesDTO dto){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }

        // Obtener Token
        String token = authorizationHeader.substring(7);
        // Obtener User
        Long userId = jwtService.extractUserId(token);

        userAppService.updateUserAllergies(userId, dto);

        return ResponseEntity.ok(userAppService.findMyUserAllergiesDTOById(jwtService.extractUserId(token)));

    }

    @PutMapping("/myPresents")
    public ResponseEntity<?> updatePresents (@RequestHeader (name = "Authorization", required = true) String authorizationHeader,
                                             @RequestBody ListUserPresentDTO dto){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }
        // Obtener Token
        String token = authorizationHeader.substring(7);
        // Obtener User
        Long userId = jwtService.extractUserId(token);

        userAppService.updateMyUserPresents(userId, dto);

        return ResponseEntity.ok(userAppService.finMyUserPresentDTOById(userId));

    }




}
