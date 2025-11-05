package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.UsuarioAlergenoDTO;
import com.myweddingplanner.back.dto.UsuarioDTO;
import com.myweddingplanner.back.model.Usuario;
import com.myweddingplanner.back.model.Alergia;
import com.myweddingplanner.back.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {

        Optional<Usuario> opt = usuarioRepository.findById(id);

        return (opt.isEmpty()) ? Optional.empty() : Optional.of(toDTO(opt.get()));
    }

    @Override
    public List<UsuarioDTO> findAll() {

        List<UsuarioDTO> list = new ArrayList<>();

        for (Usuario u: usuarioRepository.findAll()){
            list.add(toDTO(u));
        }
        return list;
    }

    @Override
    public UsuarioDTO save(UsuarioDTO dto) {

        Usuario entity = (dto.getId() != null)
                ? usuarioRepository.findById(dto.getId()).orElseGet(Usuario::new)
                : new Usuario();

        entity.setNombre(dto.getNombre());
        entity.setApellido1(dto.getApellido1());
        entity.setApellido2(dto.getApellido2());
        entity.setEmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());

        List<Alergia> alergias = (dto.getAlergias() == null) ? List.of()
                : dto.getAlergias().stream().map(this::toEntityUsuarioAlergeno).toList();

        entity.setAlergias(alergias);


        Usuario saved = usuarioRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    public void deleteById(Long id) {

        usuarioRepository.deleteById(id);

    }

    @Override
    public Optional<UsuarioDTO> findByEmail(String email) {
        Optional<Usuario> opt = usuarioRepository.findByEmail(email);
        return (opt.isEmpty()) ? Optional.empty() : Optional.of(toDTO(opt.get()));
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
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

        List<UsuarioAlergenoDTO> alergias = (usuario.getAlergias() == null) ?
                List.of() : usuario.getAlergias().stream().map(this::toDTOUsuarioAlergeno).toList();

        usuarioDTO.setAlergias(new ArrayList<>(alergias));

        return usuarioDTO;
    }

    private UsuarioAlergenoDTO toDTOUsuarioAlergeno(Alergia alergia){

        UsuarioAlergenoDTO dto = new UsuarioAlergenoDTO();

        dto.setId(alergia.getId());
        dto.setId_alergeno(alergia.getIdAlergeno());
        dto.setNombre(alergia.getNombre());

        return dto;
    }

    private Alergia toEntityUsuarioAlergeno (UsuarioAlergenoDTO dto){
        Alergia e = new Alergia();
        e.setId(dto.getId());
        e.setIdAlergeno(dto.getId_alergeno());
        e.setNombre(dto.getNombre());
        return e;
    }

}
