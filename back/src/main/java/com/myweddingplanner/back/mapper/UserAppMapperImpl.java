package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.users.*;
import com.myweddingplanner.back.model.*;
import org.springframework.stereotype.Component;

@Component
public class UserAppMapperImpl implements UserAppMapper{

    @Override
    public MyUserDTO toMyUserDTO(UserApp u) {

        MyUserDTO dto = new MyUserDTO();

        dto.setIdUser(u.getId() != null ? u.getId() : null);
        dto.setName(u.getName() != null ? u.getName() : null);
        dto.setFirstSurname(u.getFirstSurname() != null ? u.getFirstSurname() : null);
        dto.setSecondSurname(u.getSecondSurname() != null ? u.getSecondSurname() : null);
        dto.setEmail(u.getEmail() != null ? u.getEmail() : null);

        // MY WEDDING
        for (UserWedding uw : u.getMyWeddings()){

            MyWedding myWedding = new MyWedding();

            myWedding.setIdWedding(uw.getWedding().getId());
            myWedding.setStateWedding(uw.getWedding().getStateWedding().toString());
            myWedding.setDate(uw.getWedding().getDateWedding());
            myWedding.setPlace(uw.getWedding().getPlace());

            dto.getMyWeddings().add(myWedding);
        }

        // MY INVITATION
        for (UserInvitationWedding ui : u.getInvitations()){

            dto.getMyInvitations().add(toMyInvitation(ui));

        }

        return dto;
    }

    @Override
    public MyUserAllergiesDTO toMyUserAllergiesDTO(UserApp u) {

        MyUserAllergiesDTO dto = new MyUserAllergiesDTO();

        dto.setUserId(u.getId());

        for (AllergiesUser a : u.getAllergies()){
            dto.getMyAllergies().add(toMyAllergy(a));
        }
        return dto;
    }

    @Override
    public MyUserPresentDTO toMyUserPresentDTO(UserApp u) {

        MyUserPresentDTO dto = new MyUserPresentDTO();

        dto.setUserId(u.getId());

        for(Present p : u.getPresents()){
            dto.getMyPresents().add(toMyPresent(p));
        }

        return dto;
    }

    private MyAllergy toMyAllergy(AllergiesUser a){

        MyAllergy dto = new MyAllergy();

        dto.setIdAllergy(a.getId() != null ? a.getId() : null);
        dto.setIdAllergen(a.getAllergen().getId() != null ? a.getAllergen().getId() : null);
        dto.setName(a.getAllergen().getName() != null ? a.getAllergen().getName() : null);
        dto.setImage("pendiente de mapper");

        return dto;

    }

    private MyPresent toMyPresent(Present p){

        MyPresent dto = new MyPresent();

        dto.setIdPresent(p.getId() != null ? p.getId() : null);
        dto.setIdWedding(p.getWedding().getId() != null ? p.getWedding().getId() : null);
        dto.setIdProduct(p.getProduct().getId() != null ? p.getProduct().getId() : null);
        dto.setProductName(p.getProduct().getName() != null ? p.getProduct().getName() : null);
        dto.setConfirm(p.isConfirm());
        dto.setPrice(p.getPrice());

        return dto;
    }

    private MyInvitation toMyInvitation(UserInvitationWedding ui){

        MyInvitation invitation = new MyInvitation();

        invitation.setIdInvitation(ui.getId());
        invitation.setWeddingDate(ui.getWedding().getDateWedding());
        invitation.setPlace(ui.getWedding().getPlace());

        // COUPLE
        boolean first = true;
        for (UserWedding groom : ui.getWedding().getGrooms()){

            String name = "";

            if (first) {
                name = groom.getUserApp().getName();
                first = false;
            } else {
                name = name + " y " + groom.getUserApp().getName();
            }
        }

        invitation.setConfirm(ui.isConfirm());

        invitation.setNotified(ui.isNotified());

        for (Companion companion : ui.getCompanions()){
            invitation.getCompanions().add(toMyCompanion(companion));
        }

        return invitation;
    }

    private MyCompanion toMyCompanion (Companion companion){

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
