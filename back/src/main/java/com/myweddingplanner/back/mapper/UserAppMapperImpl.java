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
            dto.getMyWeddings().add(toMyWedding(uw.getWedding()));
        }

        dto.setUserAllergies(user.getAllergies());

        return dto;
    }

    @Override
    public MyWedding toMyWedding(Wedding wedding) {

        MyWedding myWedding = new MyWedding();

        myWedding.setIdWedding(wedding.getId());
        myWedding.setStateWedding(wedding.getStateWedding().toString());
        myWedding.setDate(wedding.getDateWedding());
        myWedding.setPlace(wedding.getPlace());

        return myWedding;
    }


    @Override
    public ListUserPresentDTO toListUserPresent(UserApp u) {

        ListUserPresentDTO dto = new ListUserPresentDTO();

        dto.setUserId(u.getId());

        for(Present p : u.getPresents()){
            dto.getMyPresents().add(toMyPresent(p));
        }

        return dto;
    }

    @Override
    public MyPresent toMyPresent(Present p){

        MyPresent dto = new MyPresent();

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

        for(UserInvitationWedding invitation : user.getInvitations()){
            dto.getInvitationList().add(toMyInvitation(invitation));
        }
        return dto;
    }

    @Override
    public MyInvitation toMyInvitation(UserInvitationWedding userInvitationWedding){

        MyInvitation invitation = new MyInvitation();

        invitation.setIdInvitation(userInvitationWedding.getId());
        invitation.setWeddingDate(userInvitationWedding.getWedding().getDateWedding());
        invitation.setPlace(userInvitationWedding.getWedding().getPlace());

        // COUPLE
        boolean first = true;
        for (UserWedding groom : userInvitationWedding.getWedding().getGrooms()){

            String name = "";

            if (first) {
                name = groom.getUserApp().getName();
                first = false;
            } else {
                name = name + " y " + groom.getUserApp().getName();
            }
        }

        invitation.setConfirm(userInvitationWedding.isConfirm());

        invitation.setNotified(userInvitationWedding.isNotified());

        for (Companion companion : userInvitationWedding.getCompanions()){
            invitation.getCompanions().add(toMyCompanion(companion));
        }

        return invitation;
    }

    @Override
    public MyCompanion toMyCompanion (Companion companion){

        MyCompanion myCompanion = new MyCompanion();

        myCompanion.setIdCompanion(companion.getId());
        myCompanion.setName(companion.getName());
        myCompanion.setFirstSurname(companion.getFirstSurname());
        myCompanion.setSecondSurname(companion.getSecondSurname());
        myCompanion.setEmail(companion.getEmail());
        myCompanion.setAdult(companion.isAdult());
        myCompanion.setAllergies(companion.getAllergies());

        return myCompanion;
    }

}
