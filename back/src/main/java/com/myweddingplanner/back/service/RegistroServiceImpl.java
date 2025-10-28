package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.*;
import com.myweddingplanner.back.exception.EmailYaRegistradoException;
import com.myweddingplanner.back.model.Boda;
import com.myweddingplanner.back.model.Novio;
import com.myweddingplanner.back.model.Rol;
import com.myweddingplanner.back.model.Usuario;
import com.myweddingplanner.back.repository.RolRepository;
import com.myweddingplanner.back.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistroServiceImpl implements RegistroService{

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder encoder;
    private final BodaService bodaService;
    private final NovioService novioService;

    @Override
    @Transactional
    public RegistroResult registrarUsuario(RegisterRequest req) {

        // Comprobar si ya esta registrado algun usuario con este email
        if (usuarioService.existsByEmail(req.getEmail())){
            throw new EmailYaRegistradoException(req.getEmail());
        }

        // continuamos con el registro de nuevo usuario
        // instanciamos Rol
        Rol rolUsuario = rolRepository.findByNombre("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("Rol no creado en BBDD"));


        // Crear Usuario base
        Usuario nuevo = new Usuario();
        nuevo.setEmail(req.getEmail());
        nuevo.setPassword(encoder.encode(req.getPassword()));
        nuevo.setRol(rolUsuario);

        // TODO definir todos loc campos del formulario de registro de usuario

        Usuario usuarioCreado = usuarioRepository.save(nuevo);

        // si es novio hay que gestionarlo
        if (req.isEsNovio()){
            procesarRegistroNovio(req, usuarioCreado);
        }

        return new RegistroResult(usuarioCreado.getId(), usuarioCreado.getEmail(), rolUsuario.getNombre());    }

    public void procesarRegistroNovio(RegisterRequest req, Usuario usuarioCreado) {

        // Caso 1: el usuario ya existe como novio de una boda => hay que completar sus datos de novio solamente
        if (novioService.existsByEmail(usuarioCreado.getEmail())){
            System.out.println("Usuario figura como novio");

            Novio novioActualizar = novioService.findByEmail(usuarioCreado.getEmail())
                    .orElseThrow(() -> new IllegalStateException("Novio no encontrado"));

            Boda boda = novioActualizar.getBoda();

            if (boda == null){
                throw new IllegalStateException("Inconsistencia, novio no tiene boda");
            }

            // Actualizamos el novio si coincide por email
            for (Novio n : boda.getNovios()){
                if (usuarioCreado.getEmail().equals(n.getEmail())){
                    n.setIdUsuario(usuarioCreado.getId());
                    n.setNombre(usuarioCreado.getNombre());
                    n.setApellido1(usuarioCreado.getApellido1());
                    n.setApellido2(usuarioCreado.getApellido2());
                    n.setEmail(usuarioCreado.getEmail());
                    n.setTelefono(usuarioCreado.getTelefono());
                }
            }

            // persistir boda
            bodaService.save(bodaService.toDTO(boda));
            return;
        }

        // Caso 2: el usuario no existe como novio => crear boda con este usuario como Novio1
        System.out.println("creando boda y asignando novio1");

        BodaDTO nuevaBoda = new BodaDTO();

        nuevaBoda.setLugar("pendiente"); // TODO definir si viene desde UI
        nuevaBoda.setFecha("pendiente"); // TODO definir si viene desde UI

        NovioDTO novio1 = new NovioDTO();

        novio1.setIdUsuario(usuarioCreado.getId());
        novio1.setNombre(usuarioCreado.getNombre());
        novio1.setApellido1(usuarioCreado.getApellido1());
        novio1.setApellido2(usuarioCreado.getApellido2());
        novio1.setEmail(usuarioCreado.getEmail());
        novio1.setTelefono(usuarioCreado.getTelefono());

        nuevaBoda.getNovios().add(novio1);

        // siempre viene email novio2
        if (req.getEmailNovio() != null && !req.getEmailNovio().isBlank()){

            NovioDTO novio2 = new NovioDTO();

            if (usuarioService.existsByEmail(req.getEmailNovio())) {
                // novio2 ya es usuario del sistema, completamos datos
                UsuarioDTO u2 = usuarioService.findByEmail(req.getEmailNovio())
                        .orElseThrow(() -> new IllegalStateException("Inconsistencia usuario novio2 no encontrado"));
                novio2.setIdUsuario(u2.getId());
                novio2.setNombre(u2.getNombre());
                novio2.setApellido1(u2.getApellido1());
                novio2.setApellido2(u2.getApellido2());
                novio2.setEmail(u2.getEmail());
                novio2.setTelefono(u2.getTelefono());
            } else {
                // Novio2 no registrado
                novio2.setEmail(req.getEmailNovio());
            }

            nuevaBoda.getNovios().add(novio2);
        }

        bodaService.save(nuevaBoda);

    }
}
