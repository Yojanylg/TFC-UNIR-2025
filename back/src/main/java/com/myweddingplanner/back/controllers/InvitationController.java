package com.myweddingplanner.back.controllers;

import com.myweddingplanner.back.dto.users.MyInvitation;
import com.myweddingplanner.back.service.UserInvitationWeddingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invitations")
@Tag(name = "API invitations",
        description = "CRUD invitations de la App")
public class InvitationController {

    private final UserInvitationWeddingService userInvitationWeddingService;

    public InvitationController(UserInvitationWeddingService userInvitationWeddingService) {
        this.userInvitationWeddingService = userInvitationWeddingService;
    }

    // modifica el estado de las invitaciones a notificado
    @PutMapping("/notified/{id}")
    public ResponseEntity<?> notified(@PathVariable Long id){
        return userInvitationWeddingService.notified(id) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // actualiza el listado de acompañantes de un invitado
    @PutMapping("/companion")
    public ResponseEntity<?> updateCompanion(@RequestBody MyInvitation myInvitation){

        userInvitationWeddingService.updateCompanion(myInvitation);

        return ResponseEntity.ok().build();

    }
}
