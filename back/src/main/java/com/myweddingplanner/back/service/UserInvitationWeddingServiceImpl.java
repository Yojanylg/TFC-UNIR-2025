package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.users.ListUserInvitationDTO;
import com.myweddingplanner.back.dto.users.UserCompanionDTO;
import com.myweddingplanner.back.dto.users.UserInvitationDTO;
import com.myweddingplanner.back.mapper.UserAppMapper;
import com.myweddingplanner.back.model.Companion;
import com.myweddingplanner.back.model.UserInvitation;
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
    public ListUserInvitationDTO getMyListInvitations(Long userId) {

        ListUserInvitationDTO dto = new ListUserInvitationDTO();

        dto.setUserId(userId);

        for (UserInvitation invitation : userInvitationWeddingRepository.findByUserAppId(userId)) {
            dto.getInvitationList().add(userAppMapper.toMyInvitation(invitation));
        }

        return dto;
    }

    @Override
    public boolean notified(Long id) {

        UserInvitation invitation = userInvitationWeddingRepository.findById(id).orElseThrow();

        invitation.setNotified(true);

        UserInvitation nuevo = userInvitationWeddingRepository.save(invitation);

        return (nuevo.getId()!=null);
    }

    @Override
    public void updateCompanion(UserInvitationDTO userInvitationDTO) {

        UserInvitation invitationWedding = userInvitationWeddingRepository
                .findById(userInvitationDTO.getIdInvitation())
                .orElseThrow();

        List<Companion> companionsEntity = invitationWedding.getCompanions();
        List<UserCompanionDTO> companionsDTO = userInvitationDTO.getCompanions();

        List<Companion> toRemove = new ArrayList<>();

        for (Companion companion : companionsEntity){

            boolean found = false;

            for (UserCompanionDTO userCompanionDTO : companionsDTO){

                if(companion.getId().equals(userCompanionDTO.getIdCompanion())){

                    companion.setName(userCompanionDTO.getName());
                    companion.setFirstSurname(userCompanionDTO.getFirstSurname());
                    companion.setSecondSurname(userCompanionDTO.getSecondSurname());
                    companion.setEmail(userCompanionDTO.getEmail());
                    companion.setAdult(userCompanionDTO.isAdult());
                    companion.setAllergies(userCompanionDTO.getAllergies());

                    found = true;
                    break;
                }


            }

            if (!found){
                toRemove.add(companion);
            }
        }

        companionsEntity.removeAll(toRemove);

        for (UserCompanionDTO userCompanionDTO : companionsDTO){

            if (userCompanionDTO.getIdCompanion()==null){

                Companion nueva = new Companion();

                nueva.setName(userCompanionDTO.getName());
                nueva.setFirstSurname(userCompanionDTO.getFirstSurname());
                nueva.setSecondSurname(userCompanionDTO.getSecondSurname());
                nueva.setEmail(userCompanionDTO.getEmail());
                nueva.setAdult(userCompanionDTO.isAdult());
                nueva.setAllergies(userCompanionDTO.getAllergies());
                nueva.setUserInvitationWedding(invitationWedding);

                companionsEntity.add(nueva);
            }
        }

        userInvitationWeddingRepository.save(invitationWedding);
    }
}
