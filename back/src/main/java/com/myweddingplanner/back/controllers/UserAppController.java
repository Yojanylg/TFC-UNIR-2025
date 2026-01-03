package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.users.MyUserDTO;
import com.myweddingplanner.back.dto.users.ListUserPresentDTO;
import com.myweddingplanner.back.dto.users.ListUserInvitationDTO;
import com.myweddingplanner.back.security.JwtService;
import com.myweddingplanner.back.service.UserAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(
        name = "API usuarios",
        description = "CRUD usuarios de la App")
@RestController
@RequestMapping("/api/users")
public class UserAppController {

    private final UserAppService userAppService;
    private final JwtService jwtService;

    public UserAppController(UserAppService userAppService, JwtService jwtService) {
        this.userAppService = userAppService;
        this.jwtService = jwtService;
    }


    /* ----------------- READ DE USUARIOS ----------------- */

    // ----------------------------------------------------
    // -------------------- MI USUARIO --------------------
    // ----------------------------------------------------
    @Operation(
            summary = "Obtener mi perfil",
            description = "Devuelve la información del usuario, su perfil. " +
                    "Se obtiene el userId del token enviado en la cabecera"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Perfil obtenido correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MyUserDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado, falta el token en la caebecera",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    ),
                    headers = @Header(
                            name = "Authorization",
                            description = "Debe enviarse como: Bearer <token>"
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping("/me")
    public ResponseEntity<?> getUser (
            @RequestHeader (name = "Authorization", required = true) String authorizationHeader
    ){

        // Obtener Token
        String token = authorizationHeader.substring(7);
        // Obtener usuario
        MyUserDTO dto = userAppService.getMyUser(jwtService.extractUserId(token));

        return ResponseEntity.ok(dto);

    }


    // ----------------------------------------------------
    // -------------------- MI LISTA DE REGALOS -----------
    // ----------------------------------------------------
    @Operation(
            summary = "Obtener mis regalos",
            description = "Devuelve el listado con los regalos que el usuario ha hecho en las " +
                    "distintas bodas a las que ha asistido como invitado. Se obtiene el userId del token enviado en la cabecera"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de regalos obtenido correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ListUserPresentDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado, falta el token en la caebecera",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    ),
                    headers = @Header(
                            name = "Authorization",
                            description = "Debe enviarse como: Bearer <token>"
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping("/myPresents")
    public ResponseEntity<?> getUserPresents (
            @RequestHeader (name = "Authorization", required = true) String authorizationHeader
    ){

        // Obtener Token
        String token = authorizationHeader.substring(7);

        return ResponseEntity.ok(userAppService.getListUserPresent(jwtService.extractUserId(token)));

    }


    // ----------------------------------------------------
    // -------------------- MI LISTA DE INVITACIONES ------
    // ----------------------------------------------------
    @Operation(
            summary = "Obtener mi lista de invitaciones",
            description = "Devuelve el listado con las invitaciones que el usuario ha recibido " +
                    " para asistir a bodas. Se obtiene el userId del token enviado en la cabecera"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de invitaciones obtenido correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ListUserInvitationDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado, falta el token en la caebecera",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    ),
                    headers = @Header(
                            name = "Authorization",
                            description = "Debe enviarse como: Bearer <token>"
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping("/myInvitations")
    public ResponseEntity<?> getUserInvitations (
            @RequestHeader (name = "Authorization", required = true) String authorizationHeader
    ){

        // Obtener Token
        String token = authorizationHeader.substring(7);

        return ResponseEntity.ok(userAppService.getListUserInvitation(jwtService.extractUserId(token)));

    }



    /* ----------------- ACTUALIZACIONES DE USUARIOS ----------------- */


    // ----------------------------------------------------
    // --------------------ACTUALIZAR MI USUARIO ----------
    // ----------------------------------------------------
    @Operation(
            summary = "Actualiza mi perfil",
            description = "Actualiza la información del usuario, su perfil. " +
                    "Se obtiene el userId del token enviado en la cabecera"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Perfil actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MyUserDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado, falta el token en la caebecera",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    ),
                    headers = @Header(
                            name = "Authorization",
                            description = "Debe enviarse como: Bearer <token>"
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Petición inválida"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @PutMapping("/me")
    public ResponseEntity<?> updateUser (
            @RequestHeader (name = "Authorization", required = true) String authorizationHeader,
            @RequestBody MyUserDTO dto
    ){

        // TODO actualizar usuario y devolverlo actualizado
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/myPresents")
    public ResponseEntity<?> updatePresents (@RequestHeader (name = "Authorization", required = true) String authorizationHeader,
                                             @RequestBody ListUserPresentDTO dto){

        return ResponseEntity.ok(userAppService.updateListUserPresent(dto));

    }


    @PutMapping("/myInvitations")
    public ResponseEntity<?> updateUserInvitations (@RequestHeader (name = "Authorization", required = true) String authorizationHeader,
                                                    @RequestBody ListUserInvitationDTO dto){


        // Obtener Token
        String token = authorizationHeader.substring(7);
        Long userId = jwtService.extractUserId(token);
        dto.setUserId(userId);

        return ResponseEntity.ok(userAppService.updateListUserInvitation(dto));

    }


}
