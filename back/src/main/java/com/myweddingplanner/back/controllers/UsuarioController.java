package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.MeDTO;
import com.myweddingplanner.back.dto.MiBodaDTO;
import com.myweddingplanner.back.dto.MiInvitacionDTO;
import com.myweddingplanner.back.dto.UsuarioDTO;
import com.myweddingplanner.back.model.Invitacion;
import com.myweddingplanner.back.model.Novio;
import com.myweddingplanner.back.security.JwtService;
import com.myweddingplanner.back.service.InvitadoService;
import com.myweddingplanner.back.service.NovioService;
import com.myweddingplanner.back.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "API usuarios",
        description = "CRUD usuarios de la App")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final NovioService novioService;
    private final InvitadoService invitadoService;

    public UsuarioController(UsuarioService usuarioService, JwtService jwtService, NovioService novioService, InvitadoService invitadoService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
        this.novioService = novioService;
        this.invitadoService = invitadoService;
    }

    // READ ALL - ADMIN
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getall(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id){
        if (usuarioService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioService.findById(id).get());
    }

    // READ ONE ME
    @GetMapping("/me")
    public ResponseEntity<?> getMyUserId(@RequestHeader (name = "Authorization", required = true) String authorizationHeader){

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Falta token Bearer en Authorization"));

        }

        String token = authorizationHeader.substring(7);

        try{
            Long userId = jwtService.extractUserId(token);

            final UsuarioDTO usuario = usuarioService.findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

            MeDTO meDTO = new MeDTO();
            meDTO.setUserId(userId);
            meDTO.setUsuario(usuario);

            // Mis bodas como novio
            List<MiBodaDTO> misBoda = new ArrayList<>();
            List<Novio> novios = novioService.findByUsuario(userId);

            for (Novio n : novios){

                MiBodaDTO miBoda = new MiBodaDTO();

                miBoda.setIdBoda(n.getBoda().getId());
                miBoda.setLugar(n.getBoda().getLugar());
                miBoda.setFecha(n.getBoda().getFecha());
                miBoda.setEstado(n.getBoda().getEstado().name());

                misBoda.add(miBoda);

            }

            // Mis bodas como invitado
            List<MiInvitacionDTO> misInvitaciones = new ArrayList<>();
            List<Invitacion> invitaciones = invitadoService.findByUsuario(userId);

            for(Invitacion i : invitaciones){

                MiInvitacionDTO miInvitacion = new MiInvitacionDTO();

                miInvitacion.setIdBoda(i.getBoda().getId());
                miInvitacion.setIdInvitacion(i.getId());
                miInvitacion.setLugarBoda(i.getBoda().getLugar());
                miInvitacion.setFechaBoda(i.getBoda().getFecha());
                miInvitacion.setConfirmado(i.isConfirmado());

                StringBuilder nombreNovios = new StringBuilder();
                boolean primero = true;

                for (Novio n : i.getBoda().getNovios()){
                    if (primero){
                        nombreNovios.append(n.getNombre());
                        primero = false;
                    } else {
                        nombreNovios.append(" y ").append(n.getNombre());
                    }
                }

                miInvitacion.setNombreNovios(nombreNovios.toString());

                misInvitaciones.add(miInvitacion);
            }

            meDTO.setMisBodas(misBoda);
            meDTO.setMisInvitaciones(misInvitaciones);


            return ResponseEntity.ok(meDTO);

        } catch (io.jsonwebtoken.JwtException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Token invalido o expirado"));
        } catch (Exception ex){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error interno al construir /me"));
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO dto){

        return usuarioService.findById(id)
                .map(existing -> {
                    dto.setId(id);
                    UsuarioDTO actualizado = usuarioService.save(dto);
                    return ResponseEntity.ok(actualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO dto){

        UsuarioDTO nuevo = usuarioService.save(dto);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo.getId())
                .toUri();

        return ResponseEntity.created(location).body(nuevo);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (usuarioService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
