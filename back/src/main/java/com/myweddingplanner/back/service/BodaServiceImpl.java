package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.*;
import com.myweddingplanner.back.model.*;
import com.myweddingplanner.back.repository.BodaRepository;
import com.myweddingplanner.back.repository.ItinerarioRepository;
import com.myweddingplanner.back.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BodaServiceImpl implements BodaService{

    private final BodaRepository bodaRepository;
    private final ItinerarioRepository itinerarioRepository;
    private final UsuarioRepository usuarioRepository;

    public BodaServiceImpl(BodaRepository bodaRepository, ItinerarioRepository itinerarioRepository, UsuarioRepository usuarioRepository) {
        this.bodaRepository = bodaRepository;
        this.itinerarioRepository = itinerarioRepository;
        this.usuarioRepository = usuarioRepository;
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
        entity.setEstado(dto.getEstado());

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
        List<Invitacion> inv = (dto.getInvitados() == null) ? List.of()
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
    public List<BodaDTO> buscarBodasPorUsuario(Long usuario) {

        String emailUsuario = usuarioRepository.findById(usuario).get().getEmail();

        List<Boda> bodasUsuario = bodaRepository.findByNoviosEmail(emailUsuario);

        List<BodaDTO> bodasDtoUsuario = new ArrayList<>();

        for (Boda boda : bodasUsuario){
            bodasDtoUsuario.add(toDTO(boda));
        }

        return bodasDtoUsuario;
    }

    @Override
    public Optional<BodaDTO> buscarBodaActualPorUsuario(Long usuario) {

        String emailUsuario = usuarioRepository.findById(usuario).get().getEmail();

        List<Boda> bodasUsuario = bodaRepository.findByNoviosEmail(emailUsuario);

        for (Boda boda : bodasUsuario){
            if (boda.getEstado().name().equals("PREPARANDO")){
                return Optional.of(toDTO(boda));
            }
        }

        return Optional.empty();
    }

    @Override
    public BodaDTO toDTO(Boda boda){

        BodaDTO dto = new BodaDTO();

        dto.setId(boda.getId());
        dto.setFecha(boda.getFecha());
        dto.setLugar(boda.getLugar());
        dto.setEstado(boda.getEstado());

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

    @Override
    public MiBodaDTO toMisBodaDTO(Boda boda) {

        MiBodaDTO dto = new MiBodaDTO();

        dto.setIdBoda(boda.getId());
        dto.setLugar(boda.getLugar());
        dto.setFecha(boda.getFecha());
        dto.setEstado(boda.getEstado().name());

        return dto;
    }

    public Boda toEntity(BodaDTO dto){
        Boda boda = new Boda();

        if(dto.getId() != null) boda.setId(dto.getId());

        boda.setLugar(dto.getLugar());
        boda.setFecha(dto.getFecha());

        // tratamiento de un string a enum
        // boda.setEstado(EstadoBoda.valueOf(dto.getEstado()));
        boda.setEstado(dto.getEstado());


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

            List<Invitacion> invitacions = new ArrayList<>();

            for (InvitadoDTO invitadoDTO : dto.getInvitados()){
                Invitacion invitacion = new Invitacion();
                invitacion.setId(invitadoDTO.getId());
                invitacion.setNombre(invitadoDTO.getNombre());
                invitacion.setApellido1(invitadoDTO.getApellido1());
                invitacion.setApellido2(invitadoDTO.getApellido2());
                invitacion.setEmail(invitadoDTO.getEmail());
                invitacion.setTelefono(invitadoDTO.getTelefono());
                invitacion.setConfirmado(invitadoDTO.isConfirmado());
                invitacion.setAcomptesMayores(invitadoDTO.getAcomptesMayores());
                invitacion.setAcomptesMenores(invitadoDTO.getAcomptesMenores());

                invitacions.add(invitacion);
            }
            boda.setInvitados(invitacions);
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
        dto.setUsuario(novio.getUsuario());
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
        novio.setUsuario(dto.getUsuario());
        novio.setNombre(dto.getNombre());
        novio.setApellido1(dto.getApellido1());
        novio.setApellido2(dto.getApellido2());
        novio.setEmail(dto.getEmail());
        novio.setTelefono(dto.getTelefono());

        return novio;
    }

    private InvitadoDTO toDTOInvitado(Invitacion invitacion) {

        InvitadoDTO dto = new InvitadoDTO();
        dto.setId(invitacion.getId());
        dto.setIdUsuario(invitacion.getUsuario());
        dto.setNombre(invitacion.getNombre());
        dto.setApellido1(invitacion.getApellido1());
        dto.setApellido2(invitacion.getApellido2());
        dto.setEmail(invitacion.getEmail());
        dto.setTelefono(invitacion.getTelefono());
        dto.setConfirmado(invitacion.isConfirmado());
        dto.setAcomptesMayores(invitacion.getAcomptesMayores());
        dto.setAcomptesMenores(invitacion.getAcomptesMenores());

        return dto;
    }

    private Invitacion toEntityInvitado(InvitadoDTO dto){
        Invitacion invitacion = new Invitacion();

        invitacion.setId(dto.getId());
        invitacion.setUsuario(dto.getIdUsuario());
        invitacion.setNombre(dto.getNombre());
        invitacion.setApellido1(dto.getApellido1());
        invitacion.setApellido2(dto.getApellido2());
        invitacion.setEmail(dto.getEmail());
        invitacion.setTelefono(dto.getTelefono());
        invitacion.setConfirmado(dto.isConfirmado());
        invitacion.setAcomptesMayores(dto.getAcomptesMayores());
        invitacion.setAcomptesMenores(dto.getAcomptesMenores());

        return invitacion;
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
