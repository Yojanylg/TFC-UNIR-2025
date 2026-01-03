package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.*;
import com.myweddingplanner.back.exception.EmailYaRegistradoException;
import com.myweddingplanner.back.model.*;
import com.myweddingplanner.back.model.enums.StateWedding;
import com.myweddingplanner.back.repository.RolRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    private final UserAppService userAppService;
    private final RolRepository rolRepository;
    private final PasswordEncoder encoder;
    private final WeddingService weddingService;
    private final UserWeddingService userWeddingService;


    @Override
    @Transactional
    public RegisterResult registerUserApp(RegisterRequest req) {

        if (userAppService.existsByEmail(req.getEmail())){
            throw new EmailYaRegistradoException(req.getEmail());
        }

        Rol rolUsuario = rolRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("Rol no creado en BBDD"));


        UserApp newUser = new UserApp();
        newUser.setName(req.getName());
        newUser.setFirstSurname(req.getFirstSurname());
        newUser.setSecondSurname(req.getSecondSurname());
        newUser.setEmail(req.getEmail());
        newUser.setPassword(encoder.encode(req.getPassword()));
        newUser.setRol(rolUsuario);

        UserApp userAppCreated = userAppService.save(newUser);

        if (req.isGroom()) processGroomRegistration(req, userAppCreated);

        return new RegisterResult(userAppCreated.getId(), userAppCreated.getEmail(), rolUsuario.getName());
    }

    @Transactional
    private void processGroomRegistration(RegisterRequest req, UserApp userAppCreated) {

        // Caso 1: nuevo usuario registrado
        //          -> existe como novio de una boda
        //          => recuperar boda y settear como novio
        if (userWeddingService.existsByEmailGroom(userAppCreated.getEmail())){

            UserWedding userWeddingToUpdate = userWeddingService.findByEmailGroom(userAppCreated.getEmail());

            Wedding wedding = userWeddingToUpdate.getWedding();

            if (wedding == null){
                throw new IllegalStateException("Inconsistencia, novio no tiene boda");
            }

            // Actualizamos el novio si coincide por email
            for (UserWedding n : wedding.getGrooms()){
                if (userAppCreated.getEmail().equals(n.getEmailGroom())){

                    n.setUserApp(userAppCreated);

                }
            }

            weddingService.save(wedding);
            return;
        }

        // Caso 2: el usuario no existe como novio => crear boda con este usuario como Novio1

        Wedding wedding = new Wedding();

        wedding.setDateWedding(req.getWeddingDate());
        // TODO agregar tratamiento del lugar de la boda
        wedding.setPlace("pendiente");
        wedding.setStateWedding(StateWedding.PREPARING);

        //Wedding weddingCreated = weddingService.save(newWedding);

        UserWedding uwFirst = new UserWedding();

        uwFirst.setWedding(wedding);
        uwFirst.setUserApp(userAppCreated);
        uwFirst.setEmailGroom(userAppCreated.getEmail());
        wedding.getGrooms().add(uwFirst);

        // setter uwSecond

        if (req.getEmailGroom() != null && !req.getEmailGroom().isBlank()){

            UserWedding uwSecond = new UserWedding();


            if (userAppService.existsByEmail(req.getEmailGroom())) {

                // novio2 ya es usuario del sistema, completamos datos
                UserApp u2 = userAppService.findByEmail(req.getEmailGroom())
                        .orElseThrow(() -> new IllegalStateException("Inconsistencia usuario novio2 no encontrado"));

                uwSecond.setUserApp(u2);
                uwSecond.setWedding(wedding);

            } else {

                // Novio2 no registrado
                uwSecond.setEmailGroom(req.getEmailGroom());
                uwSecond.setWedding(wedding);
            }

            wedding.getGrooms().add(uwSecond);
        }

        weddingService.save(wedding);

    }

}
