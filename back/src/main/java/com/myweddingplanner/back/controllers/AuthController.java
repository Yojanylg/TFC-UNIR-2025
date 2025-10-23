package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.AuthResponse;
import com.myweddingplanner.back.dto.LoginRequest;
import com.myweddingplanner.back.dto.RegisterRequest;
import com.myweddingplanner.back.model.Rol;
import com.myweddingplanner.back.model.Usuario;
import com.myweddingplanner.back.repository.RolRepository;
import com.myweddingplanner.back.repository.UsuarioRepository;
import com.myweddingplanner.back.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final PasswordEncoder encoder;

    public AuthController(AuthenticationManager authManager, JwtService jwtService,
                          UsuarioRepository usuarioRepo, RolRepository rolRepo,
                          PasswordEncoder encoder) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        if (usuarioRepo.existsByEmail(req.getEmail())) {
            // evita user enumeration: puedes usar un 409 y mensaje genérico si prefieres
            return ResponseEntity.status(409).build();
        }

        Rol rolUser = rolRepo.findByNombre("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("Falta semilla: crea ROLE_USER"));

        var u = new Usuario();
        u.setEmail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setRol(rolUser);
        usuarioRepo.save(u);

        String access = jwtService.generateToken(
                u.getEmail(), Map.of("role", u.getRol().getNombre(), "uid", u.getId()));
        String refresh = jwtService.generateRefreshToken(u.getEmail());

        // 201 Created + Location (opcional)
        return ResponseEntity.created(URI.create("/api/users/" + u.getId()))
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

        // +++ ver sección 2 para cargar el rol de forma EAGER/segura
        var u = usuarioRepo.findWithRolByEmail(req.getEmail()) //
                .orElseThrow(); //  debería existir tras authenticate

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
