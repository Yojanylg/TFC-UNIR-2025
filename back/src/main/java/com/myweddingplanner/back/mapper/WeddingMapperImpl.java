package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.users.UserCompanionDTO;
import com.myweddingplanner.back.dto.wedding.*;
import com.myweddingplanner.back.model.*;
import org.springframework.stereotype.Component;

@Component
public class WeddingMapperImpl implements WeddingMapper{

    @Override
    public WeddingDTO toWeddingDTO(Wedding wedding) {

        WeddingDTO dto = new WeddingDTO();

        dto.setIdWedding(wedding.getId());
        dto.setDateWedding(wedding.getDateWedding());
        dto.setPlace(wedding.getPlace());
        dto.setStateWedding(wedding.getStateWedding().toString());

        listUserWedding(wedding, dto);

        listEvent(wedding, dto);

        return dto;
    }

    private void listUserWedding(Wedding w, WeddingDTO dto){

        for (UserWedding u : w.getGrooms()){
            dto.getGrooms().add(toUserWeddingDTO(u));
        }
    }

    private void listEvent (Wedding w, WeddingDTO dto){

        for (Event e : w.getEvents()){
            dto.getEvents().add(toMyWeddingEventDTO(e));
        }

    }

    @Override
    public ListWeddingPresentDTO toListWeddingPresentDTO(Wedding entity) {

        ListWeddingPresentDTO dto = new ListWeddingPresentDTO();

        dto.setIdWedding(entity.getId());

        for (UserWedding u : entity.getGrooms()){
            dto.getGrooms().add(toUserWeddingDTO(u));
        }

        for (Present p : entity.getPresents()){
            dto.getMyWeddingPresent().add(toMyWeddingPresentDTO(p));
        }

        return dto;
    }

    public ListWeddingInvitationDTO toListWeddingInvitationDTO (Wedding entity){


        ListWeddingInvitationDTO dto = new ListWeddingInvitationDTO();

        dto.setIdWedding(entity.getId());

        for (UserWedding u : entity.getGrooms()){
            dto.getGrooms().add(toUserWeddingDTO(u));
        }

        for (UserInvitation invitation : entity.getInvitations()){
            dto.getListWeddingInvitation().add(toMyWeddingInvitationDTO(invitation));
        }

        return dto;
    }

    @Override
    public WeddingUserDTO toUserWeddingDTO(UserWedding entity) {

        WeddingUserDTO dto = new WeddingUserDTO();

        dto.setIdUserWedding(entity.getId());
        dto.setIdUser(entity.getUserApp().getId());
        dto.setName(entity.getUserApp().getName());
        dto.setFirstSurname(entity.getUserApp().getFirstSurname());
        dto.setSecondSurname(entity.getUserApp().getSecondSurname());

        return dto;
    }

    @Override
    public WeddingEventDTO toMyWeddingEventDTO(Event entity) {

        WeddingEventDTO dto = new WeddingEventDTO();

        dto.setIdEvent(entity.getId());
        dto.setType(entity.getEventType().toString());
        dto.setDescription(entity.getDescription());
        dto.setTime(entity.getTime());

        return dto;
    }

    @Override
    public WeddingPresentDTO toMyWeddingPresentDTO(Present entity) {

        WeddingPresentDTO dto = new WeddingPresentDTO();

        dto.setIdPresent(entity.getId());
        dto.setIdProduct(entity.getProduct().getId());
        dto.setProductName(entity.getProduct().getName());
        dto.setConfirm(entity.isConfirm());
        dto.setPrice(entity.getPrice());
        dto.setUserName(entity.getUserApp().getName());
        dto.setUserFirstSurname(entity.getUserApp().getFirstSurname());

        return dto;
    }

    @Override
    public WeddingInvitationDTO toMyWeddingInvitationDTO(UserInvitation entity) {

        WeddingInvitationDTO dto = new WeddingInvitationDTO();

        dto.setIdInvitation(entity.getId());
        dto.setName(entity.getUserApp().getName());
        dto.setFirstSurname(entity.getUserApp().getFirstSurname());
        dto.setSecondSurname(entity.getUserApp().getSecondSurname());
        dto.setEmailInvitation(entity.getEmailInvitation());

        for (Companion c : entity.getCompanions()){
            dto.getCompanion().add(toMyCompanion(c));
        }

        return dto;
    }

    private UserCompanionDTO toMyCompanion (Companion entity){

        UserCompanionDTO dto = new UserCompanionDTO();

        dto.setIdCompanion(entity.getId());
        dto.setName(entity.getName());
        dto.setFirstSurname(entity.getFirstSurname());
        dto.setSecondSurname(entity.getSecondSurname());
        dto.setEmail(entity.getEmail());
        dto.setAdult(entity.isAdult());
        dto.setAllergies(entity.getAllergies());

        return dto;
    }

}
