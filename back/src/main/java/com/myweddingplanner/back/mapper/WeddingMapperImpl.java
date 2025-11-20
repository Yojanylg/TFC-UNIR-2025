package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.users.MyCompanion;
import com.myweddingplanner.back.dto.wedding.*;
import com.myweddingplanner.back.model.*;
import org.hibernate.dialect.MySQLServerConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;

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

        listPresent(wedding, dto);

        listWeddingInvitation(wedding, dto);

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

    private void listPresent(Wedding w, WeddingDTO dto){

        for (Present p : w.getPresents()){
            dto.getPresents().add(toMyWeddingPresentDTO(p));
        }
    }

    private void listWeddingInvitation(Wedding w, WeddingDTO dto){

        for (UserInvitationWedding u : w.getInvitations()){
            dto.getInvitations().add(toMyWeddingInvitationDTO(u));
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

    @Override
    public UserWeddingDTO toUserWeddingDTO(UserWedding entity) {

        UserWeddingDTO dto = new UserWeddingDTO();

        dto.setIdUserWedding(entity.getId());
        dto.setIdUser(entity.getUserApp().getId());
        dto.setName(entity.getUserApp().getName());
        dto.setFirstSurname(entity.getUserApp().getFirstSurname());
        dto.setSecondSurname(entity.getUserApp().getSecondSurname());

        return dto;
    }

    @Override
    public MyWeddingEventDTO toMyWeddingEventDTO(Event entity) {

        MyWeddingEventDTO dto = new MyWeddingEventDTO();

        dto.setIdEvent(entity.getId());
        dto.setType(entity.getEventType());
        dto.setDescription(entity.getDescription());
        dto.setTime(entity.getTime());

        return dto;
    }

    @Override
    public MyWeddingPresentDTO toMyWeddingPresentDTO(Present entity) {

        MyWeddingPresentDTO dto = new MyWeddingPresentDTO();

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
    public MyWeddingInvitationDTO toMyWeddingInvitationDTO(UserInvitationWedding entity) {

        MyWeddingInvitationDTO dto = new MyWeddingInvitationDTO();

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

    private MyCompanion toMyCompanion (Companion entity){

        MyCompanion dto = new MyCompanion();

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
