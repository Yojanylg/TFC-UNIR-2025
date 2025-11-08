package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.*;
import com.myweddingplanner.back.repository.UserAppRepository;
import com.myweddingplanner.back.security.JwtService;
import com.myweddingplanner.back.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

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


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {

        RegistroResult result = registerService.registerUserApp(req);

        String access = jwtService.generateToken(
                result.usuarioEmail(), Map.of("role", result.rolNombre(), "uid", result.usuarioId()));
        String refresh = jwtService.generateRefreshToken(result.usuarioEmail());

        // 201 Created + Location (opcional)
        return ResponseEntity
                .created(URI.create("/api/users/" + result.usuarioId()))
                .body(new AuthResponse(access, refresh));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        try {

            var authToken = new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword());
            authManager.authenticate(authToken); // lanza excepción si falla

        } catch (BadCredentialsException ex) {
            // respuesta uniforme (evita revelar si el email existe)
            return ResponseEntity.status(401).build();
        }

        var u = userAppRepository.findWithRolByEmail(req.getEmail()) //
                .orElseThrow();

        String access = jwtService.generateToken(
                u.getEmail(), Map.of("role", u.getRol().getNombre(), "uid", u.getId()));

        String refresh = jwtService.generateRefreshToken(u.getEmail());
        return ResponseEntity.ok(new AuthResponse(access, refresh));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestParam String refreshToken) {
        var username = jwtService.extractUsername(refreshToken);
        // aquí podrías validar revocación en BD/lista blanca
        String access = jwtService.generateToken(username, Map.of());
        return ResponseEntity.ok(new AuthResponse(access, refreshToken));
    }
}
