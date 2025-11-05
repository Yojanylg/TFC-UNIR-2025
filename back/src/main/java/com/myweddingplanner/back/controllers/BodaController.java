package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.BodaDTO;
import com.myweddingplanner.back.dto.NuevaInvitacionDTO;
import com.myweddingplanner.back.dto.InvitadoDTO;
import com.myweddingplanner.back.dto.UsuarioDTO;
import com.myweddingplanner.back.service.BodaService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/bodas")
@Tag(name = "API bodas",
        description = "CRUD bodas de la App")
public class BodaController {

    private final BodaService bodaService;
    private final UsuarioService usuarioService;
    private final NovioService novioService;

    public BodaController(BodaService bodaService, UsuarioService usuarioService, NovioService novioService) {
        this.bodaService = bodaService;
        this.usuarioService = usuarioService;
        this.novioService = novioService;
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<BodaDTO> getById(@PathVariable Long id){
        if (bodaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bodaService.findById(id).get());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<BodaDTO> update(@PathVariable Long id, @RequestBody BodaDTO dto){

        return bodaService.findById(id)
                .map(existing -> {
                    dto.setId(id);
                    BodaDTO actualizado = bodaService.save(dto);
                    return ResponseEntity.ok(actualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<BodaDTO> create(@RequestBody BodaDTO dto){

        BodaDTO nuevo = bodaService.save(dto);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo.getId())
                .toUri();

        return ResponseEntity.created(location).body(nuevo);
    }

    // CREATE INVITACION
    @PostMapping("/invitacion")
    public ResponseEntity<?> invitar(@RequestBody NuevaInvitacionDTO dto){

        System.out.println(dto.getIdBoda());

        Optional<BodaDTO> bodaOpt = bodaService.findById(dto.getIdBoda());
        if (bodaOpt.isEmpty()) return ResponseEntity.notFound().build();

        BodaDTO boda = bodaOpt.get();


        List<InvitadoDTO> invitadosExistentes = boda.getInvitados() != null ? boda.getInvitados() : new ArrayList<>();

        List<InvitadoDTO> invitadosNuevos = new ArrayList<>();

        // busco invitados nuevos
        if (dto.getNuevos() != null){
            for (String email : dto.getNuevos()){
                if (email == null || email.isBlank()) continue;

                boolean usuarioEncontrado = false;

                for (InvitadoDTO invitadoDTO : invitadosExistentes){

                    if (email.equals(invitadoDTO.getEmail())){
                        usuarioEncontrado = true;
                        break;
                    }
                }
                if (!usuarioEncontrado){
                    InvitadoDTO nuevaInvitacion = new InvitadoDTO();
                    nuevaInvitacion.setEmail(email);
                    invitadosNuevos.add(nuevaInvitacion);
                }

            }
        }

        // busco si estan registrados y actualizo datos
        for (InvitadoDTO invitadoDTO : invitadosNuevos){

            String email = invitadoDTO.getEmail();

            if(usuarioService.existsByEmail(email)){

                UsuarioDTO aux = usuarioService.findByEmail(email).orElse(null);

                if (aux != null){
                    invitadoDTO.setNombre(aux.getNombre());
                    invitadoDTO.setApellido1(aux.getApellido1());
                    invitadoDTO.setApellido2(aux.getApellido2());
                    invitadoDTO.setIdUsuario(aux.getId());
                }

            }
        }

        if (boda.getInvitados() == null){
            boda.setInvitados(new ArrayList<>());
        }

        boda.getInvitados().addAll(invitadosNuevos);

        BodaDTO guardada = bodaService.save(boda);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    /* ADMIN
    // DELETE - ADMIN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (bodaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        bodaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // READ ALL - ADMIN
    @GetMapping
    public ResponseEntity<List<BodaDTO>> getall(){
        return ResponseEntity.ok(bodaService.findAll());
    }

     */

}
