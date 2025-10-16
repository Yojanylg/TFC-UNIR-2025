package com.myweddingplanner.back.mappers;

import com.myweddingplanner.back.dto.*;
import com.myweddingplanner.back.model.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BodaMapper {

    @Mapping(target = "novios", source = "novios")
    @Mapping(target = "itinerario", source = "itinerario")
    @Mapping(target = "regalosBoda", source = "regalosBoda")
    @Mapping(target = "invitados", source = "invitados")
    BodaDTO toDTO(Boda entity);

    @InheritInverseConfiguration
    Boda toEntity(BodaDTO dto);

    // ------------ Itinerario ------------
    default ItinerarioDTO toDTO(Itinerario it) {
        if (it == null) return null;
        return new ItinerarioDTO(it.getId());
    }

    default Itinerario toEntity(ItinerarioDTO dto) {
        if (dto == null) return null;
        Itinerario it = new Itinerario();
        it.setId(dto.getId());
        return it;
    }

    // ------------ RegaloBoda ------------
    default RegaloBodaDTO toDTO(RegaloBoda rb) {
        if (rb == null) return null;
        return new RegaloBodaDTO(rb.getId());
    }

    default RegaloBoda toEntity(RegaloBodaDTO dto) {
        if (dto == null) return null;
        RegaloBoda rb = new RegaloBoda();
        rb.setId(dto.getId());
        return rb;
    }

    default List<RegaloBodaDTO> toRegaloBodaDTOList(List<RegaloBoda> list) {
        if (list == null) return null;
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<RegaloBoda> toRegaloBodaEntityList(List<RegaloBodaDTO> list) {
        if (list == null) return null;
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    // ------------ InvitacionUsuario ------------
    default InvitacionUsuarioDTO toDTO(InvitacionUsuario inv) {
        if (inv == null) return null;
        return new InvitacionUsuarioDTO(inv.getId());
    }

    default InvitacionUsuario toEntity(InvitacionUsuarioDTO dto) {
        if (dto == null) return null;
        InvitacionUsuario inv = new InvitacionUsuario();
        inv.setId(dto.getId());
        return inv;
    }

    default List<InvitacionUsuarioDTO> toInvitacionDTOList(List<InvitacionUsuario> list) {
        if (list == null) return null;
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<InvitacionUsuario> toInvitacionEntityList(List<InvitacionUsuarioDTO> list) {
        if (list == null) return null;
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    // ------------ BodaUsuario  ------------
    default BodaUsuarioDTO toDTO(BodaUsuario bu) {
        if (bu == null) return null;
        return new BodaUsuarioDTO(bu.getId());
    }
    default BodaUsuario toEntity(BodaUsuarioDTO dto) {
        if (dto == null) return null;
        BodaUsuario bu = new BodaUsuario();
        bu.setId(dto.getId());
        return bu;
    }

}
