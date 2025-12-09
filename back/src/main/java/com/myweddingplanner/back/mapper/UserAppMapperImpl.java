package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.users.*;
import com.myweddingplanner.back.model.*;
import org.springframework.stereotype.Component;

@Component
public class UserAppMapperImpl implements UserAppMapper{

    @Override
    public MyUserDTO toMyUserDTO(UserApp user) {

        MyUserDTO dto = new MyUserDTO();

        dto.setIdUser(user.getId() != null ? user.getId() : null);
        dto.setName(user.getName() != null ? user.getName() : null);
        dto.setFirstSurname(user.getFirstSurname() != null ? user.getFirstSurname() : null);
        dto.setSecondSurname(user.getSecondSurname() != null ? user.getSecondSurname() : null);
        dto.setEmail(user.getEmail() != null ? user.getEmail() : null);

        // MY WEDDING
        for (UserWedding uw : user.getMyWeddings()){
            dto.getUserWeddings().add(toMyWedding(uw.getWedding()));
        }

        UserAllergies userAllergies = new UserAllergies();

        userAllergies.setIdUserAllergies(user.getAllergies().getId());
        userAllergies.setIdUser(user.getId());

        userAllergies.setGluten(user.getAllergies().isGluten());
        userAllergies.setCrustaceos(userAllergies.isCrustaceos());
        userAllergies.setHuevos(userAllergies.isHuevos());
        userAllergies.setPescado(userAllergies.isPescado());
        userAllergies.setCacahuete(userAllergies.isCacahuete());
        userAllergies.setSoja(userAllergies.isSoja());
        userAllergies.setLeche(userAllergies.isLeche());
        userAllergies.setFrutosCascara(userAllergies.isFrutosCascara());
        userAllergies.setApio(userAllergies.isApio());
        userAllergies.setMostaza(userAllergies.isMostaza());
        userAllergies.setSesamo(userAllergies.isSesamo());
        userAllergies.setSulfitos(userAllergies.isSulfitos());
        userAllergies.setAltramuces(userAllergies.isAltramuces());
        userAllergies.setMoluscos(userAllergies.isMoluscos());


        dto.setUserAllergies(userAllergies);

        return dto;
    }

    @Override
    public UserWeddingDTO toMyWedding(Wedding wedding) {

        UserWeddingDTO userWeddingDTO = new UserWeddingDTO();

        userWeddingDTO.setIdWedding(wedding.getId());
        userWeddingDTO.setStateWedding(wedding.getStateWedding().toString());
        userWeddingDTO.setDate(wedding.getDateWedding());
        userWeddingDTO.setPlace(wedding.getPlace());

        return userWeddingDTO;
    }


    @Override
    public ListUserPresentDTO toListUserPresent(UserApp u) {

        ListUserPresentDTO dto = new ListUserPresentDTO();

        dto.setUserId(u.getId());

        for(Present p : u.getPresents()){
            dto.getUserPresents().add(toMyPresent(p));
        }

        return dto;
    }

    @Override
    public UserPresentDTO toMyPresent(Present p){

        UserPresentDTO dto = new UserPresentDTO();

        dto.setIdPresent(p.getId() != null ? p.getId() : null);
        dto.setIdWedding(p.getWedding().getId() != null ? p.getWedding().getId() : null);
        dto.setIdProduct(p.getProduct().getId() != null ? p.getProduct().getId() : null);
        dto.setProductName(p.getProduct().getName() != null ? p.getProduct().getName() : null);
        dto.setConfirm(p.isConfirm());
        dto.setPrice(p.getPrice());

        return dto;
    }


    @Override
    public ListUserInvitationDTO toListUserInvitation(UserApp user) {

        ListUserInvitationDTO dto = new ListUserInvitationDTO();

        dto.setUserId(user.getId());

        for(UserInvitation invitation : user.getInvitations()){
            dto.getInvitationList().add(toMyInvitation(invitation));
        }
        return dto;
    }

    @Override
    public UserInvitationDTO toMyInvitation(UserInvitation userInvitation){

        UserInvitationDTO invitation = new UserInvitationDTO();

        invitation.setIdInvitation(userInvitation.getId());
        invitation.setWeddingDate(userInvitation.getWedding().getDateWedding());
        invitation.setPlace(userInvitation.getWedding().getPlace());

        // COUPLE
        boolean first = true;
        String name = "";

        for (UserWedding groom : userInvitation.getWedding().getGrooms()){



            if (first) {
                name = groom.getUserApp().getName();
                first = false;
            } else {
                name = name + " y " + groom.getUserApp().getName();
            }
        }

        invitation.setCouple(name);

        invitation.setConfirm(userInvitation.isConfirm());

        invitation.setNotified(userInvitation.isNotified());

        for (Companion companion : userInvitation.getCompanions()){
            invitation.getCompanions().add(toMyCompanion(companion));
        }

        return invitation;
    }

    @Override
    public UserCompanionDTO toMyCompanion (Companion companion){

        UserCompanionDTO userCompanionDTO = new UserCompanionDTO();

        userCompanionDTO.setIdCompanion(companion.getId());
        userCompanionDTO.setName(companion.getName());
        userCompanionDTO.setFirstSurname(companion.getFirstSurname());
        userCompanionDTO.setSecondSurname(companion.getSecondSurname());
        userCompanionDTO.setEmail(companion.getEmail());
        userCompanionDTO.setAdult(companion.isAdult());
        userCompanionDTO.setAllergies(companion.getAllergies());

        return userCompanionDTO;
    }

}
