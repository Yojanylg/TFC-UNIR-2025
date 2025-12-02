package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.wedding.ListEmailInvitation;
import com.myweddingplanner.back.dto.wedding.*;
import com.myweddingplanner.back.mapper.WeddingMapper;
import com.myweddingplanner.back.model.*;
import com.myweddingplanner.back.model.enums.EventType;
import com.myweddingplanner.back.model.enums.StateWedding;
import com.myweddingplanner.back.repository.ProductRepository;
import com.myweddingplanner.back.repository.WeddingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeddingServiceImpl implements WeddingService{

    private final WeddingRepository weddingRepository;
    private final WeddingMapper weddingMapper;
    private final ProductRepository productRepository;

    public WeddingServiceImpl(WeddingRepository weddingRepository, WeddingMapper weddingMapper, ProductRepository productRepository) {
        this.weddingRepository = weddingRepository;
        this.weddingMapper = weddingMapper;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<WeddingDTO> getById(Long id) {

        Wedding wedding = weddingRepository.findById(id).orElseThrow();

        WeddingDTO dto = weddingMapper.toWeddingDTO(wedding);

        return Optional.of(dto);
    }

    @Override
    public Optional<WeddingDTO> getWeddingPreparingByUserId(Long userId) {

        List<Wedding> weddings = weddingRepository.findByGroomsUserAppId(userId).orElseThrow();

        for (Wedding w : weddings){
            if (w.getStateWedding().toString().equals(StateWedding.PREPARING.toString())){
                return Optional.of(weddingMapper.toWeddingDTO(w));
            }
        }

        return Optional.empty();
    }

    @Override
    public ListWeddingPresentDTO getListWeddingPresent(Long weddingId) {
        return weddingMapper.toListWeddingPresentDTO(weddingRepository.findById(weddingId).orElseThrow());
    }

    @Override
    public ListWeddingInvitationDTO getListWeddingInvitation(Long weddingId) {
        return weddingMapper.toListWeddingInvitationDTO(weddingRepository.findById(weddingId).orElseThrow());
    }

    @Override
    public WeddingDTO updateWeddingDTO(WeddingDTO dto) {

        Wedding wedding = weddingRepository.findById(dto.getIdWedding()).orElseThrow();

        wedding.setDateWedding(dto.getDateWedding());
        wedding.setPlace(dto.getPlace());
        wedding.setStateWedding(StateWedding.valueOf(dto.getStateWedding()));

        updateListWeddingEvent(wedding, dto.getEvents());

        weddingRepository.save(wedding);

        return weddingMapper.toWeddingDTO(wedding);
    }

    private void updateListWeddingEvent(Wedding wedding, List<WeddingEventDTO> listDto){

        List<Event> toRemove = new ArrayList<>();

        for (Event e : wedding.getEvents()){

            boolean found = false;

            for (WeddingEventDTO eDto : listDto){

                if (e.getId().equals(eDto.getIdEvent())){

                    updateWeddingEvent(e, eDto);
                    found = true;
                }
            }

            if(!found){
                toRemove.add(e);
            }
        }

        wedding.getEvents().removeAll(toRemove);

        for (WeddingEventDTO event : listDto){
            if (event.getIdEvent()==null){
                Event nuevo = new Event();
                nuevo.setWedding(wedding);
                nuevo.setEventType(EventType.valueOf(event.getType()));
                nuevo.setDescription(event.getDescription());
                nuevo.setTime(event.getTime());
                wedding.getEvents().add(nuevo);
            }
        }
    }

    private void updateWeddingEvent(Event e, WeddingEventDTO eDto){

        e.setEventType(EventType.valueOf(eDto.getType()));
        e.setDescription(eDto.getDescription());
        e.setTime(eDto.getTime());

    }

    @Override
    public ListWeddingPresentDTO updateListWeddingPresent(ListWeddingPresentDTO dto) {

        Wedding wedding = weddingRepository.findById(dto.getIdWedding()).orElseThrow();

        List<Present> toRemove = new ArrayList<>();

        for (Present p : wedding.getPresents()){

            boolean found = false;

            for (WeddingPresentDTO pDto : dto.getMyWeddingPresent()){

                if (p.getId().equals(pDto.getIdPresent())){

                    found = true;
                    break;
                }
            }

            if (!found) {
                toRemove.add(p);
            }
        }

        wedding.getPresents().removeAll(toRemove);

        for (WeddingPresentDTO pDto : dto.getMyWeddingPresent()){
            if (pDto.getIdPresent()==null){
                Present p = new Present();

                p.setWedding(wedding);
                p.setConfirm(false);
                p.setPrice(pDto.getPrice());
                p.setProduct(productRepository.findById(pDto.getIdProduct()).orElseThrow());

                wedding.getPresents().add(p);
            }
        }

        weddingRepository.save(wedding);

        return weddingMapper.toListWeddingPresentDTO(wedding);
    }

    @Override
    public ListWeddingInvitationDTO addInvitation(ListEmailInvitation toAdd) {

        Wedding wedding = weddingRepository.findById(toAdd.getIdWedding()).orElseThrow();

        List<UserInvitation> nuevas = new ArrayList<>();

        for (String email : toAdd.getListEmail()){

            boolean found = false;

            for (UserInvitation userInvitation : wedding.getInvitations()){
                if (email.equals(userInvitation.getEmailInvitation())){
                    found = true;
                    break;
                }
            }

            if(!found){

                UserInvitation nueva = new UserInvitation();

                nueva.setEmailInvitation(email);

                nuevas.add(nueva);

            }
        }

        wedding.getInvitations().addAll(nuevas);

        weddingRepository.save(wedding);

        return getListWeddingInvitation(wedding.getId());

    }

    @Override
    public List<WeddingDTO> findAll() {

        List<WeddingDTO> list = new ArrayList<>();

        for (Wedding w : weddingRepository.findAll()){
            list.add(weddingMapper.toWeddingDTO(w));
        }

        return list;
    }

    @Override
    public Wedding save(Wedding wedding) {
        return weddingRepository.save(wedding);
    }

    @Override
    public void deleteById(Long id) {

    }
}
