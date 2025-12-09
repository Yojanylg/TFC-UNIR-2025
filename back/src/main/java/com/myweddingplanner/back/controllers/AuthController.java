package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.*;
import com.myweddingplanner.back.model.UserApp;
import com.myweddingplanner.back.model.enums.StateWedding;
import com.myweddingplanner.back.repository.UserAppRepository;
import com.myweddingplanner.back.security.JwtService;
import com.myweddingplanner.back.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@Tag(name = "Authentication",
        description = "Controlador encargado de gestionar el registro de nuevos usuarios, la autenticación " +
                        "de los usuarios y refrescar el token de autenticación")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RegisterService registerService;
    private final UserAppRepository userAppRepository;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, UserAppRepository userAppRepository, RegisterService registerService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userAppRepository = userAppRepository;
        this.registerService = registerService;
    }

    // ----------------------------------------------------
    // -------------------- REGISTER ----------------------
    // ----------------------------------------------------
    @Operation(
            summary = "Registro de usuario",
            description = "Registra un usuario si no existe el email aportado y " +
                    "devuelve AuthResponse con el token de autenticación"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuario registrado correctamente",
                    content = @Content(
                            schema = @Schema(implementation = AuthResponse.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada invalidos"
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de registro del usuario",
            required = true,
            content = @Content(schema = @Schema(implementation = RegisterRequest.class))
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {

        System.out.println(req);

        RegisterResult result = registerService.registerUserApp(req);

        boolean haveNewInvitations = userAppRepository.existsByIdAndInvitations_IsNotified(result.usuarioId(), true);
        boolean hasWedding = userAppRepository.existsByIdAndMyWeddings_Wedding_StateWedding(result.usuarioId(), StateWedding.PREPARING);
        boolean haveInvitations = userAppRepository.existsByIdAndInvitationsIsNotEmpty(result.usuarioId());

        String access = jwtService.generateToken(
                result.usuarioEmail(), Map.of("role", result.rolNombre(), "uid", result.usuarioId()));
        String refresh = jwtService.generateRefreshToken(result.usuarioEmail());

        // 201 Created + Location (opcional)
        return ResponseEntity
                .created(URI.create("/api/users/" + result.usuarioId()))
                .body(new AuthResponse(access, refresh, haveNewInvitations, hasWedding, haveInvitations));

    }


    // ----------------------------------------------------
    // --------------------- LOGIN ------------------------
    // ----------------------------------------------------
    @Operation(
            summary = "Autenticación de usuario",
            description = "Comprueba los datos de autenticación del usuario y " +
                    "devuelve AuthResponse con el token de autenticación"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario autenticado correctamente",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Credenciales invalidas"
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Credenciales del usuario para iniciar sesion",
            required = true,
            content = @Content(schema = @Schema(implementation = LoginRequest.class))
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {

        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword());
                authManager.authenticate(authToken);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).build();
        }

        UserApp u = userAppRepository.findWithRolByEmail(req.getEmail()) //
                .orElseThrow();

        boolean haveNewInvitations = userAppRepository.existsByIdAndInvitations_IsNotified(u.getId(), true);
        boolean hasWedding = userAppRepository.existsByIdAndMyWeddings_Wedding_StateWedding(u.getId(), StateWedding.PREPARING);
        boolean haveInvitations = userAppRepository.existsByIdAndInvitationsIsNotEmpty(u.getId());

        String access = jwtService.generateToken(
                u.getEmail(), Map.of("role", u.getRol().getName(), "uid", u.getId()));

        String refresh = jwtService.generateRefreshToken(u.getEmail());

        return ResponseEntity.ok(new AuthResponse(access, refresh, haveNewInvitations, hasWedding, haveInvitations));
    }

    // ----------------------------------------------------
    // --------------------- REFRESH TOKEN ----------------
    // ----------------------------------------------------
    @Operation(
            summary = "Refresh del token de autenticación",
            description = "Recibe un refresh token valido y devuelve un nuevo acces token"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Token refrescado correctamente",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Refresh token invalido"
            )
    })
    @Parameter(
            name = "refreshToken",
            description = "Refresh token valido previamente entregado",
            required = true,
            example = "pendiente"
    )
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestParam String refreshToken) {
        var username = jwtService.extractUsername(refreshToken);
        String access = jwtService.generateToken(username, Map.of());
        return ResponseEntity.ok(new AuthResponse(access, refreshToken));
    }
}
