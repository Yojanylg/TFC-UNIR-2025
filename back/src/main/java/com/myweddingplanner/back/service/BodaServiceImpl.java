package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.*;
import com.myweddingplanner.back.model.*;
import com.myweddingplanner.back.repository.BodaRepository;
import com.myweddingplanner.back.repository.ItinerarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BodaServiceImpl implements BodaService{

    private final BodaRepository bodaRepository;
    private final ItinerarioRepository itinerarioRepository;

    public BodaServiceImpl(BodaRepository bodaRepository, ItinerarioRepository itinerarioRepository) {
        this.bodaRepository = bodaRepository;
        this.itinerarioRepository = itinerarioRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BodaDTO> findById(Long id) {

        Optional<Boda> opt = bodaRepository.findById(id);

        return (opt.isEmpty()) ? Optional.empty() : Optional.of(toDTO(opt.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BodaDTO> findAll() {

        List<BodaDTO> list = new ArrayList<>();

        for (Boda a: bodaRepository.findAll()){
            list.add(toDTO(a));
        }
        return list;
    }

    @Override
    public BodaDTO save(BodaDTO dto) {

        Boda entity = (dto.getId() != null)
                ? bodaRepository.findById(dto.getId()).orElseGet(Boda::new)
                : new Boda();


        entity.setFecha(dto.getFecha());
        entity.setLugar(dto.getLugar());

        // AGREGANDO ITINERARIO
        Itinerario itinerario;

        if (dto.getItinerario() != null){
            ItinerarioDTO itinerarioDTO = dto.getItinerario();
            if (itinerarioDTO.getId()!= null){
                itinerario = itinerarioRepository.findById(itinerarioDTO.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Itinerario no encontrado"));
                itinerario.setDescripcion(itinerarioDTO.getDescripcion());
            } else {
                itinerario = toEntity(itinerarioDTO);
            }
            entity.setItinerario(itinerario);
            itinerario.setBoda(entity);
        }

        // AGREGANDO NOVIOS
        List<Novio> nv = (dto.getNovios() == null) ? List.of()
                : dto.getNovios().stream().map(this::toEntityNovio).toList();
        entity.setNovios(nv);

        // AGREGANDO INVITADOS
        List<Invitado> inv = (dto.getInvitados() == null) ? List.of()
                : dto.getInvitados().stream().map(this::toEntityInvitado).toList();
        entity.setInvitados(inv);

        // AGREGANDO REGALOS
        List<Regalo> reg = (dto.getRegalos() == null) ? List.of()
                : dto.getRegalos().stream().map(this::toEntityRegalo).toList();
        entity.setRegalos(reg);


        Boda saved = bodaRepository.save(entity);
        return toDTO(saved);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        bodaRepository.deleteById(id);

    }

    @Override
    public BodaDTO toDTO(Boda boda){

        BodaDTO dto = new BodaDTO();

        dto.setId(boda.getId());
        dto.setFecha(boda.getFecha());
        dto.setLugar(boda.getLugar());

        // SETEANDO ITINERARIO
        Itinerario itinerario = boda.getItinerario();

        dto.setItinerario(itinerario != null ? toDTO(itinerario) : null);

        //SETEANDO NOVIOS
        List<NovioDTO> novios = (boda.getNovios() == null) ?
                List.of() : boda.getNovios().stream().map(this::toDTONovio).toList();

        dto.setNovios(new ArrayList<>(novios));

        //SETEANDO INVITADOS
        List<InvitadoDTO> invitadoDTOS = (boda.getInvitados() == null) ?
                List.of() : boda.getInvitados().stream().map(this::toDTOInvitado).toList();
        dto.setInvitados(new ArrayList<>(invitadoDTOS));

        //SETEANDO REGALOS
        List<RegaloDTO> regalosDTO = (boda.getRegalos() == null) ?
                List.of() : boda.getRegalos().stream().map(this::toDTORegalo).toList();
        dto.setRegalos(new ArrayList<>(regalosDTO));


        return dto;
    }

    public Boda toEntity(BodaDTO dto){
        Boda boda = new Boda();

        if(dto.getId() != null) boda.setId(dto.getId());

        boda.setLugar(dto.getLugar());
        boda.setFecha(dto.getFecha());

        // SETEANDO ITINERARIO
        if (dto.getItinerario() != null) {
            Itinerario it = toEntity(dto.getItinerario());
            boda.setItinerario(it);
            it.setBoda(boda);
        }

        //SETEANDO NOVIOS

        if (dto.getNovios() != null) {
            List<Novio> novios = new ArrayList<>();

            for (NovioDTO novioDTO : dto.getNovios()){
                Novio novio = new Novio();
                novio.setId(novioDTO.getId());
                novio.setNombre(novioDTO.getNombre());
                novio.setApellido1(novioDTO.getApellido1());
                novio.setApellido2(novioDTO.getApellido2());
                novio.setEmail(novioDTO.getEmail());
                novio.setTelefono(novioDTO.getTelefono());
                novios.add(novio);
            }
            boda.setNovios(novios);
        }

        //SETEANDO INVITADOS
        if (dto.getInvitados() != null) {

            List<Invitado> invitados = new ArrayList<>();

            for (InvitadoDTO invitadoDTO : dto.getInvitados()){
                Invitado invitado = new Invitado();
                invitado.setId(invitadoDTO.getId());
                invitado.setNombre(invitadoDTO.getNombre());
                invitado.setApellido1(invitadoDTO.getApellido1());
                invitado.setApellido2(invitadoDTO.getApellido2());
                invitado.setEmail(invitadoDTO.getEmail());
                invitado.setTelefono(invitadoDTO.getTelefono());
                invitado.setConfirmado(invitadoDTO.isConfirmado());
                invitado.setAcomptesMayores(invitadoDTO.getAcomptesMayores());
                invitado.setAcomptesMenores(invitadoDTO.getAcomptesMenores());

                invitados.add(invitado);
            }
            boda.setInvitados(invitados);
        }

        //SETEANDO REGALOS

        if (dto.getRegalos() != null) {

            List<Regalo> regalos = new ArrayList<>();

            for (RegaloDTO regaloDTO : dto.getRegalos()){

                Regalo regalo = new Regalo();

                regalo.setId(regaloDTO.getId());
                regalo.setIdProducto(regaloDTO.getIdProducto());
                regalo.setDescripcion(regaloDTO.getDescripcion());
                regalo.setEnlaceCompra(regaloDTO.getEnlace());
                regalo.setIdUsuario(regaloDTO.getIdUsuario());
                regalo.setValor(regaloDTO.getValor());
                regalo.setConfirmado(regaloDTO.isConfirmado());

                regalos.add(regalo);
            }
            boda.setRegalos(regalos);
        }

        return boda;
    }

    private ItinerarioDTO toDTO(Itinerario itinerario) {
        ItinerarioDTO dto = new ItinerarioDTO();
        dto.setId(itinerario.getId());
        dto.setDescripcion(itinerario.getDescripcion());
        return dto;
    }

    private Itinerario toEntity(ItinerarioDTO dto){
        Itinerario itinerario = new Itinerario();

        itinerario.setId(dto.getId());
        itinerario.setDescripcion(dto.getDescripcion());

        return itinerario;
    }

    private NovioDTO toDTONovio(Novio novio) {

        NovioDTO dto = new NovioDTO();
        dto.setId(novio.getId());
        dto.setIdUsuario(novio.getIdUsuario());
        dto.setNombre(novio.getNombre());
        dto.setApellido1(novio.getApellido1());
        dto.setApellido2(novio.getApellido2());
        dto.setEmail(novio.getEmail());
        dto.setTelefono(novio.getTelefono());

        return dto;
    }

    private Novio toEntityNovio(NovioDTO dto){
        Novio novio = new Novio();

        novio.setId(dto.getId());
        novio.setIdUsuario(dto.getIdUsuario());
        novio.setNombre(dto.getNombre());
        novio.setApellido1(dto.getApellido1());
        novio.setApellido2(dto.getApellido2());
        novio.setEmail(dto.getEmail());
        novio.setTelefono(dto.getTelefono());

        return novio;
    }

    private InvitadoDTO toDTOInvitado(Invitado invitado) {

        InvitadoDTO dto = new InvitadoDTO();
        dto.setId(invitado.getId());
        dto.setIdUsuario(invitado.getIdUsuario());
        dto.setNombre(invitado.getNombre());
        dto.setApellido1(invitado.getApellido1());
        dto.setApellido2(invitado.getApellido2());
        dto.setEmail(invitado.getEmail());
        dto.setTelefono(invitado.getTelefono());
        dto.setConfirmado(invitado.isConfirmado());
        dto.setAcomptesMayores(invitado.getAcomptesMayores());
        dto.setAcomptesMenores(invitado.getAcomptesMenores());

        return dto;
    }

    private Invitado toEntityInvitado(InvitadoDTO dto){
        Invitado invitado = new Invitado();

        invitado.setId(dto.getId());
        invitado.setIdUsuario(dto.getIdUsuario());
        invitado.setNombre(dto.getNombre());
        invitado.setApellido1(dto.getApellido1());
        invitado.setApellido2(dto.getApellido2());
        invitado.setEmail(dto.getEmail());
        invitado.setTelefono(dto.getTelefono());
        invitado.setConfirmado(dto.isConfirmado());
        invitado.setAcomptesMayores(dto.getAcomptesMayores());
        invitado.setAcomptesMenores(dto.getAcomptesMenores());

        return invitado;
    }

    // Mapeado Regalo <-> RegaloDTO

    private RegaloDTO toDTORegalo(Regalo regalo) {

        RegaloDTO dto = new RegaloDTO();

        dto.setId(regalo.getId());
        dto.setIdProducto(regalo.getIdProducto());
        dto.setDescripcion(regalo.getDescripcion());
        dto.setEnlace(regalo.getEnlaceCompra());
        dto.setIdUsuario(regalo.getIdUsuario());
        dto.setValor(regalo.getValor());
        dto.setConfirmado(regalo.isConfirmado());

        return dto;
    }

    private Regalo toEntityRegalo(RegaloDTO dto){

        Regalo regalo = new Regalo();

        regalo.setId(dto.getId());
        regalo.setIdProducto(dto.getIdProducto());
        regalo.setDescripcion(dto.getDescripcion());
        regalo.setEnlaceCompra(dto.getEnlace());
        regalo.setIdUsuario(dto.getIdUsuario());
        regalo.setValor(dto.getValor());
        regalo.setConfirmado(dto.isConfirmado());

        return regalo;
    }

}
