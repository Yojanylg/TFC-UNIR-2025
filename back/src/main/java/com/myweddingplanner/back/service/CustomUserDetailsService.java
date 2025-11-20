package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.UserApp;
import com.myweddingplanner.back.repository.UserAppRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAppRepository repository;

    public CustomUserDetailsService(UserAppRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserApp u = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        var authorities = List.of(new SimpleGrantedAuthority(u.getRol().getName()));
        return new User(u.getEmail(), u.getPassword(), authorities);
    }
}
