package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.users.UserCompanionDTO;
import com.myweddingplanner.back.dto.wedding.*;
import com.myweddingplanner.back.model.*;
import com.myweddingplanner.back.model.enums.StateWedding;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class WeddingMapperImpl implements WeddingMapper{

    @Override
    public WeddingDTO toWeddingDTO(Wedding wedding) {

        WeddingDTO dto = new WeddingDTO();
        if (wedding == null ) return dto;

        dto.setIdWedding(wedding.getId());

        dto.setDateWedding(comprobarString(wedding.getDateWedding()));
        dto.setPlace(comprobarString(wedding.getPlace()));

        dto.setStateWedding(wedding.getStateWedding() != null ? wedding.getStateWedding().toString() : StateWedding.PREPARING.toString());

        if (!wedding.getGrooms().isEmpty()) {
            procesarListUserWedding(wedding, dto);
        }

        if (!wedding.getEvents().isEmpty()){
            procesarListEvent(wedding, dto);
        }

        return dto;
    }

    private void procesarListUserWedding(Wedding w, WeddingDTO dto){

        for (UserWedding u : w.getGrooms()){
            dto.getGrooms().add(toUserWeddingDTO(u));
        }
    }

    private void procesarListEvent(Wedding w, WeddingDTO dto){

        for (Event e : w.getEvents()){
            dto.getEvents().add(toMyWeddingEventDTO(e));
        }

    }

    @Override
    public ListWeddingPresentDTO toListWeddingPresentDTO(Wedding entity) {

        ListWeddingPresentDTO dto = new ListWeddingPresentDTO();
        if (entity == null) return dto;

        dto.setIdWedding(entity.getId());

        if (!entity.getGrooms().isEmpty()) {
            for (UserWedding u : entity.getGrooms()){
                dto.getGrooms().add(toUserWeddingDTO(u));
            }
        }

        if (!entity.getPresents().isEmpty()){
            for (Present p : entity.getPresents()){
                dto.getMyWeddingPresent().add(toMyWeddingPresentDTO(p));
            }
        }

        return dto;
    }

    public ListWeddingInvitationDTO toListWeddingInvitationDTO (Wedding entity){


        ListWeddingInvitationDTO dto = new ListWeddingInvitationDTO();

        if (entity == null) {
            return dto;
        }

        dto.setIdWedding(entity.getId());

        if (!entity.getGrooms().isEmpty()){
            for (UserWedding u : entity.getGrooms()){
                dto.getGrooms().add(toUserWeddingDTO(u));
            }
        }


        if (!entity.getInvitations().isEmpty()) {
            for (UserInvitation invitation : entity.getInvitations()){
                dto.getListWeddingInvitation().add(toMyWeddingInvitationDTO(invitation));
            }
        }

        return dto;
    }

    @Override
    public WeddingUserDTO toUserWeddingDTO(UserWedding entity) {

        WeddingUserDTO dto = new WeddingUserDTO();
        if (entity == null) return dto;
        dto.setIdUserWedding(entity.getId());

        UserApp user = entity.getUserApp();
        if (user == null ) return dto;

        dto.setIdUser(user.getId() != null ? user.getId() : null);
        dto.setName(comprobarString(user.getName()));
        dto.setFirstSurname(comprobarString(user.getFirstSurname()));
        dto.setSecondSurname(comprobarString(user.getSecondSurname()));

        return dto;
    }

    @Override
    public WeddingEventDTO toMyWeddingEventDTO(Event entity) {

        WeddingEventDTO dto = new WeddingEventDTO();
        if (entity == null) return dto;

        dto.setIdEvent(entity.getId());

        dto.setType(entity.getEventType() != null ? entity.getEventType().name() : null);
        dto.setDescription(comprobarString(entity.getDescription()));
        dto.setTime(comprobarString(entity.getTime()));

        return dto;
    }

    @Override
    public WeddingPresentDTO toMyWeddingPresentDTO(Present entity) {

        WeddingPresentDTO dto = new WeddingPresentDTO();
        if (entity == null) return dto;

        dto.setIdPresent(entity.getId());

        Product product = entity.getProduct();

        if (product != null) {
            dto.setIdProduct(product.getId());
            dto.setProductName(comprobarString(product.getName()));
        } else {
            dto.setIdProduct(null);
            dto.setProductName("");
        }


        dto.setConfirm(entity.isConfirm());
        dto.setPrice(entity.getPrice());

        UserApp user = entity.getUserApp();

        if (user != null ){
            dto.setUserName(comprobarString(user.getName()));
            dto.setUserFirstSurname(comprobarString(user.getFirstSurname()));
        } else {
            dto.setUserName("");
            dto.setUserFirstSurname("");
        }

        return dto;
    }

    @Override
    public WeddingInvitationDTO toMyWeddingInvitationDTO(UserInvitation entity) {

        WeddingInvitationDTO dto = new WeddingInvitationDTO();
        if (entity == null) return dto;

        Wedding wedding = entity.getWedding();

        dto.setIdWedding(wedding != null ? wedding.getId() : null);

        dto.setIdInvitation(entity.getId());

       dto.setConfirm(entity.isConfirm());

        UserApp user = entity.getUserApp();

        if (user != null){
            dto.setName(comprobarString(user.getName()));
            dto.setFirstSurname(comprobarString(user.getFirstSurname()));
            dto.setSecondSurname(comprobarString(user.getSecondSurname()));
        } else {
            dto.setName("");
            dto.setFirstSurname("");
            dto.setSecondSurname("");
        }

        dto.setEmailInvitation(comprobarString(entity.getEmailInvitation()));

        if (entity.getCompanions() == null){
            dto.setCompanion(new ArrayList<>());
        } else {
            for (Companion c : entity.getCompanions()){
                dto.getCompanion().add(toMyCompanion(c));
            }
        }


        return dto;
    }

    private UserCompanionDTO toMyCompanion (Companion entity){

        UserCompanionDTO dto = new UserCompanionDTO();
        if (entity == null) return dto;

        dto.setIdCompanion(entity.getId());
        dto.setName(entity.getName() != null ? entity.getName() : null);
        dto.setFirstSurname(entity.getFirstSurname() != null ? entity.getFirstSurname() : null);
        dto.setSecondSurname(entity.getSecondSurname() != null ? entity.getSecondSurname() : null);
        dto.setEmail(entity.getEmail() != null ? entity.getEmail() : null);
        dto.setAdult(entity.isAdult());
        dto.setAllergies(entity.getAllergies() != null ? entity.getAllergies() : null);

        return dto;
    }

    private String comprobarString(String s){
        return s == null ? "" : s;
    }

}
