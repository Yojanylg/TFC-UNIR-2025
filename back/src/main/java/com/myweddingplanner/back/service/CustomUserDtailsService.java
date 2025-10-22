package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Usuario;
import com.myweddingplanner.back.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDtailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public CustomUserDtailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        var authorities = List.of(new SimpleGrantedAuthority(u.getRol().getNombre()));
        return new User(u.getEmail(), u.getPassword(), authorities);
    }
}
