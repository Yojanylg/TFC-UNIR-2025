package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.UsuarioAlergenoDTO;
import com.myweddingplanner.back.dto.UsuarioDTO;
import com.myweddingplanner.back.model.Usuario;
import com.myweddingplanner.back.model.UsuarioAlergeno;
import com.myweddingplanner.back.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository repository;


    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {

        Optional<Usuario> opt = repository.findById(id);

        return (opt.isEmpty()) ? Optional.empty() : Optional.of(toDTO(opt.get()));
    }

    @Override
    public List<UsuarioDTO> findAll() {

        List<UsuarioDTO> list = new ArrayList<>();

        for (Usuario u: repository.findAll()){
            list.add(toDTO(u));
        }
        return list;
    }

    @Override
    public UsuarioDTO save(UsuarioDTO dto) {

        Usuario entity = (dto.getId() != null)
                ? repository.findById(dto.getId()).orElseGet(Usuario::new)
                : new Usuario();

        entity.setNombre(dto.getNombre());
        entity.setApellido1(dto.getApellido1());
        entity.setApellido2(dto.getApellido2());
        entity.setEmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());

        List<UsuarioAlergeno> alergenos = (dto.getAlergenos() == null) ? List.of()
                : dto.getAlergenos().stream().map(this::toEntityUsuarioAlergeno).toList();

        entity.setAlergenos(alergenos);


        Usuario saved = repository.save(entity);
        return toDTO(saved);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);

    }

    @Override
    public UsuarioDTO toDTO(Usuario usuario) {

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido1(usuario.getApellido1());
        usuarioDTO.setApellido2(usuario.getApellido2());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefono(usuario.getTelefono());

        List<UsuarioAlergenoDTO> alergias = (usuario.getAlergenos() == null) ?
                List.of() : usuario.getAlergenos().stream().map(this::toDTOUsuarioAlergeno).toList();

        usuarioDTO.setAlergenos(new ArrayList<>(alergias));

        return usuarioDTO;
    }

    @Override
    public Usuario toEntity(UsuarioDTO dto) {

        Usuario usuario = new Usuario();

        if (dto.getId() != null) usuario.setId(dto.getId());

        usuario.setNombre(dto.getNombre());
        usuario.setApellido1(dto.getApellido1());
        usuario.setApellido2(dto.getApellido2());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());

        if (dto.getAlergenos() != null) {

            List<UsuarioAlergeno> usuarioAlergenos = new ArrayList<>();

            for (UsuarioAlergenoDTO aux : dto.getAlergenos()){

                UsuarioAlergeno usuarioAlergeno = new UsuarioAlergeno();

                usuarioAlergeno.setId(aux.getId());
                usuarioAlergeno.setNombre(aux.getNombre());
                usuarioAlergeno.setId_alergeno(aux.getId_alergeno());

                usuarioAlergenos.add(usuarioAlergeno);
            }

            usuario.setAlergenos(usuarioAlergenos);

        }

        return usuario;
    }

    private UsuarioAlergenoDTO toDTOUsuarioAlergeno(UsuarioAlergeno usuarioAlergeno){

        UsuarioAlergenoDTO dto = new UsuarioAlergenoDTO();

        dto.setId(usuarioAlergeno.getId());
        dto.setId_alergeno(usuarioAlergeno.getId_alergeno());
        dto.setNombre(usuarioAlergeno.getNombre());

        return dto;
    }

    private UsuarioAlergeno toEntityUsuarioAlergeno (UsuarioAlergenoDTO dto){
        UsuarioAlergeno e = new UsuarioAlergeno();
        e.setId(dto.getId());
        e.setId_alergeno(dto.getId_alergeno());
        e.setNombre(dto.getNombre());
        return e;
    }

}
