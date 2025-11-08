package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.users.*;
import com.myweddingplanner.back.model.*;

public class UserAppMapperImpl implements UserAppMapper{
    @Override
    public MyUserDTO toMyUserDTO(UserApp u) {

        MyUserDTO dto = new MyUserDTO();

        dto.setIdUser(u.getId() != null ? u.getId() : null);
        dto.setName(u.getName() != null ? u.getName() : null);
        dto.setFirstSurname(u.getFirstSurname() != null ? u.getFirstSurname() : null);
        dto.setSecondSurname(u.getSecondSurname() != null ? u.getSecondSurname() : null);
        dto.setEmail(u.getEmail() != null ? u.getEmail() : null);

        return dto;
    }

    @Override
    public MyUserAllergenDTO toMyUserAllergenDTO(UserApp u) {

        MyUserAllergenDTO dto = new MyUserAllergenDTO();

        for (AllergiesUser a : u.getAllergies()){
            dto.getMyAllergies().add(toMyAllergyDTO(a));
        }

        return dto;
    }

    private MyAllergy toMyAllergyDTO(AllergiesUser a){

        MyAllergy dto = new MyAllergy();

        dto.setIdAllergy(a.getId() != null ? a.getId() : null);
        dto.setIdAllergen(a.getAllergen().getId() != null ? a.getAllergen().getId() : null);
        dto.setName(a.getAllergen().getName() != null ? a.getAllergen().getName() : null);
        dto.setImage("pendiente de mapper");

        return dto;

    }

    @Override
    public MyUserPresentDTO toMyUserPresentDTO(UserApp u) {

        MyUserPresentDTO dto = new MyUserPresentDTO();

        dto.setUserId(u.getId());

        for(Present p : u.getPresents()){
            dto.getMyPresents().add(toMyPresentDTO(p));
        }

        return dto;
    }

    private MyPresent toMyPresentDTO(Present p){

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
    public MyUserInvitationWeddingDTO toMyUserInvitationWeddingDTO(UserApp u) {

        MyUserInvitationWeddingDTO dto = new MyUserInvitationWeddingDTO();

        dto.setIdUser(u.getId());

        for (UserInvitationWedding iw : u.getInvitations()){
            dto.getMyInvitations().add(toMyInvitation(iw));
        }

        return dto;
    }

    private MyInvitation toMyInvitation(UserInvitationWedding u){

        MyInvitation dto = new MyInvitation();

        dto.setIdInvitation(u.getId() != null ? u.getId() : null);
        dto.setDate(u.getWedding().getDateWedding() != null ? u.getWedding().getDateWedding() : null);
        dto.setPlace(u.getWedding().getPlace() != null ? u.getWedding().getPlace() : null);

        StringBuilder couple = new StringBuilder();

        boolean first = true;
        for (UserWedding uw : u.getWedding().getGrooms()){
            if (first) {
                couple.append(uw.getUserApp().getName()).append(" y ");
                first = false;
            } else{
                couple.append(uw.getUserApp().getName());
            }
        }

        dto.setCouple(couple.toString());

        dto.setConfirm(u.isConfirm());
        dto.setAdultCompanion(u.getAdultAcompanion());
        dto.setChildCompanion(u.getChildAcompanion());

        return dto;
    }

    @Override
    public MyUserWeddingDTO toMyUserWeddingDTO(UserApp u) {

        MyUserWeddingDTO dto = new MyUserWeddingDTO();


        return dto;
    }
}
