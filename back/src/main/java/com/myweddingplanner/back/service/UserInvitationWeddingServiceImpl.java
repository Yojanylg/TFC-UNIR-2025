package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.users.MyCompanion;
import com.myweddingplanner.back.dto.users.MyInvitation;
import com.myweddingplanner.back.mapper.UserAppMapper;
import com.myweddingplanner.back.model.Companion;
import com.myweddingplanner.back.model.UserInvitationWedding;
import com.myweddingplanner.back.repository.UserInvitationWeddingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInvitationWeddingServiceImpl implements UserInvitationWeddingService {

    private final UserInvitationWeddingRepository userInvitationWeddingRepository;
    private final UserAppMapper userAppMapper;


    public UserInvitationWeddingServiceImpl(UserInvitationWeddingRepository userInvitationWeddingRepository, UserAppMapper userAppMapper) {
        this.userInvitationWeddingRepository = userInvitationWeddingRepository;
        this.userAppMapper = userAppMapper;
    }


    @Override
    public List<MyInvitation> getMyListInvitations(Long userId) {

        List<MyInvitation> dto = new ArrayList<>();

        for (UserInvitationWedding invitation : userInvitationWeddingRepository.findByUserAppId(userId))

            dto.add(userAppMapper.toMyInvitation(invitation));

        return dto;
    }

    @Override
    public boolean notified(Long id) {

        UserInvitationWedding invitation = userInvitationWeddingRepository.findById(id).orElseThrow();

        invitation.setNotified(true);

        UserInvitationWedding nuevo = userInvitationWeddingRepository.save(invitation);

        return (nuevo.getId()!=null);
    }

    @Override
    public void updateCompanion(MyInvitation myInvitation) {

        UserInvitationWedding invitationWedding = userInvitationWeddingRepository
                .findById(myInvitation.getIdInvitation())
                .orElseThrow();

        List<Companion> companionsEntity = invitationWedding.getCompanions();
        List<MyCompanion> companionsDTO = myInvitation.getCompanions();

        List<Companion> toRemove = new ArrayList<>();

        for (Companion companion : companionsEntity){

            boolean found = false;

            for (MyCompanion myCompanion : companionsDTO){

                if(companion.getId().equals(myCompanion.getIdCompanion())){

                    companion.setName(myCompanion.getName());
                    companion.setFirstSurname(myCompanion.getFirstSurname());
                    companion.setSecondSurname(myCompanion.getSecondSurname());
                    companion.setEmail(myCompanion.getEmail());
                    companion.setAdult(myCompanion.isAdult());
                    companion.setAllergies(myCompanion.getAllergies());

                    found = true;
                    break;
                }


            }

            if (!found){
                toRemove.add(companion);
            }
        }

        companionsEntity.removeAll(toRemove);

        for (MyCompanion myCompanion : companionsDTO){

            if (myCompanion.getIdCompanion()==null){

                Companion nueva = new Companion();

                nueva.setName(myCompanion.getName());
                nueva.setFirstSurname(myCompanion.getFirstSurname());
                nueva.setSecondSurname(myCompanion.getSecondSurname());
                nueva.setEmail(myCompanion.getEmail());
                nueva.setAdult(myCompanion.isAdult());
                nueva.setAllergies(myCompanion.getAllergies());
                nueva.setUserInvitationWedding(invitationWedding);

                companionsEntity.add(nueva);
            }
        }

        userInvitationWeddingRepository.save(invitationWedding);
    }
}
