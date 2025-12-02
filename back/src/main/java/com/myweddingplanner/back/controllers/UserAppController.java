package com.myweddingplanner.back.controllers;

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
    public ResponseEntity<?> getUser (@RequestHeader (name = "Authorization", required = true) String authorizationHeader){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }

        // Obtener Token
        String token = authorizationHeader.substring(7);
        MyUserDTO dto = userAppService.getMyUser(jwtService.extractUserId(token));

        return ResponseEntity.ok(dto);

    }

    @GetMapping("/myPresents")
    public ResponseEntity<?> getUserPresents (@RequestHeader (name = "Authorization", required = true) String authorizationHeader){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }

        // Obtener Token
        String token = authorizationHeader.substring(7);

        return ResponseEntity.ok(userAppService.getListUserPresent(jwtService.extractUserId(token)));

    }

    @GetMapping("/myInvitations")
    public ResponseEntity<?> getUserInvitations (@RequestHeader (name = "Authorization", required = true) String authorizationHeader){

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

    @PutMapping("/me")
    public ResponseEntity<?> updateUser (@RequestHeader (name = "Authorization", required = true) String authorizationHeader,
                                         @RequestBody MyUserDTO dto){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }

        // Obtener Token
        String token = authorizationHeader.substring(7);
        Long userId = jwtService.extractUserId(token);



        return ResponseEntity.ok(dto);

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



        return ResponseEntity.ok(userAppService.getListUserPresent(userId));

    }
    @PutMapping("/myInvitations")
    public ResponseEntity<?> updateUserInvitations (@RequestHeader (name = "Authorization", required = true) String authorizationHeader){

        // Tratamiento del Header sin Token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));
        }

        // Obtener Token
        String token = authorizationHeader.substring(7);

        return ResponseEntity.ok(userInvitationWeddingService.getMyListInvitations(jwtService.extractUserId(token)));

    }


}
