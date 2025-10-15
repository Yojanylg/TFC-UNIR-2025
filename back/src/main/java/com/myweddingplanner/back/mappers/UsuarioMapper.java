package com.myweddingplanner.back.mappers;

import com.myweddingplanner.back.dto.UsuarioDTO;
import com.myweddingplanner.back.model.Usuario;

public final class UsuarioMapper {

    private UsuarioMapper(){}

    public static UsuarioDTO toDTO(Usuario usuario){

        if (usuario == null) return null;

        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellido1(usuario.getApellido1());
        dto.setApellido2(usuario.getApellido2());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());

        return dto;
    }

    public static Usuario toEntity(UsuarioDTO dto){

        if (dto == null) return null;

        Usuario usuario = new Usuario();

        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido1(dto.getApellido1());
        usuario.setApellido2(dto.getApellido2());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());

        return usuario;
    }
}
