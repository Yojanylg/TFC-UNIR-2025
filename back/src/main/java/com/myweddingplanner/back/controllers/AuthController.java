package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.AuthResponse;
import com.myweddingplanner.back.dto.LoginRequest;
import com.myweddingplanner.back.dto.RegisterRequest;
import com.myweddingplanner.back.model.Rol;
import com.myweddingplanner.back.model.Usuario;
import com.myweddingplanner.back.repository.RolRepository;
import com.myweddingplanner.back.repository.UsuarioRepository;
import com.myweddingplanner.back.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public AuthResponse register(@RequestBody RegisterRequest req) {
        if (usuarioRepo.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email en uso");
        }

        // Busca el rol por nombre
        Rol rolUser = rolRepo.findByNombre("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rol ROLE_USER no existe, crea datos iniciales"));

        var u = new Usuario();
        u.setEmail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setRol(rolUser);
        usuarioRepo.save(u);

        String access = jwtService.generateToken(
                u.getEmail(),
                Map.of("role", u.getRol().getNombre(), "uid", u.getId())
        );
        String refresh = jwtService.generateRefreshToken(u.getEmail());
        return new AuthResponse(access, refresh);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {
        var authToken = new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword());
        authManager.authenticate(authToken);

        var u = usuarioRepo.findByEmail(req.getEmail()).orElseThrow();
        String access = jwtService.generateToken(
                u.getEmail(),
                Map.of("role", u.getRol().getNombre(), "uid", u.getId())
        );
        String refresh = jwtService.generateRefreshToken(u.getEmail());
        return new AuthResponse(access, refresh);
    }
}
