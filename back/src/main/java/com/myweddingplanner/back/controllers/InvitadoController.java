package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.InvitadoDTO;
import com.myweddingplanner.back.model.Invitacion;
import com.myweddingplanner.back.service.InvitadoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invitados")
@Tag(name = "API invitados",
        description = "CRUD invitados de la App")
public class InvitadoController {

    private final InvitadoService invitadoService;

    public InvitadoController(InvitadoService invitadoService) {
        this.invitadoService = invitadoService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<InvitadoDTO> getById(@PathVariable Long id){
        if (invitadoService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        // TODO: QUE EL SERVICE ME DEVUELVA UN DTO
        return ResponseEntity.ok(invitadoService.toDTO(invitadoService.findById(id).get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvitadoDTO> updateInvitacion(@PathVariable Long id, @RequestBody InvitadoDTO invitadoDTO){

        System.out.println(" Ha llegado una peticion " + id);

        Invitacion invitacion = invitadoService.findById(id).get();

        System.out.println("tengo recuperado el siguiente invitado " + invitacion.toString());

        invitacion.setConfirmado(invitadoDTO.isConfirmado());
        invitacion.setAcomptesMenores(invitadoDTO.getAcomptesMenores());
        invitacion.setAcomptesMayores(invitadoDTO.getAcomptesMayores());


        Invitacion nuevo = invitadoService.save(invitacion);

        return ResponseEntity.ok(invitadoService.toDTO(invitacion));
    }
}
