package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.users.*;
import com.myweddingplanner.back.exception.UserNotFoundException;
import com.myweddingplanner.back.mapper.UserAppMapper;
import com.myweddingplanner.back.model.*;
import com.myweddingplanner.back.repository.PresentRepository;
import com.myweddingplanner.back.repository.UserAppRepository;
import com.myweddingplanner.back.repository.UserInvitationWeddingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAppServiceImpl implements UserAppService{

    private final UserAppRepository userRepository;
    private final PresentRepository presentRepository;
    private final UserInvitationWeddingRepository userInvitationWeddingRepository;
    private final UserAppMapper userAppMapper;

    public UserAppServiceImpl(UserAppRepository userRepository, PresentRepository presentRepository, UserInvitationWeddingRepository userInvitationWeddingRepository, UserAppMapper userAppMapper) {
        this.userRepository = userRepository;
        this.presentRepository = presentRepository;
        this.userInvitationWeddingRepository = userInvitationWeddingRepository;
        this.userAppMapper = userAppMapper;
    }

    @Override
    public MyUserDTO getMyUser(Long userId) {

        return userRepository.findById(userId)
                .map(userAppMapper::toMyUserDTO)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public ListUserInvitationDTO getListUserInvitation(Long userId) {

        ListUserInvitationDTO dto = new ListUserInvitationDTO();

        dto.setUserId(userId);

        List<UserInvitation> invitations = userInvitationWeddingRepository.findByUserAppId(userId);

        for (UserInvitation invitation : invitations){
            dto.getInvitationList().add(userAppMapper.toMyInvitation(invitation));
        }

        return dto;
    }

    @Override
    public ListUserPresentDTO getListUserPresent(Long userId) {
        return userRepository.findById(userId)
                .map(userAppMapper::toListUserPresent)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }



    @Override
    public MyUserDTO updateMyUser(MyUserDTO dto) {

        UserApp user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new UserNotFoundException(dto.getIdUser()));

        // todo comprobar si hay cambios y actualizar

        user.setName(dto.getName());
        user.setFirstSurname(dto.getFirstSurname());
        user.setSecondSurname(dto.getSecondSurname());
        // TODO ALLERGIES

        return userAppMapper.toMyUserDTO(userRepository.save(user));
    }

    @Override
    public ListUserInvitationDTO updateListUserInvitation(ListUserInvitationDTO dto) {

        // el usuario solo puede cambiar el estado de sus invitaciones
        // no puede add ni delete

        // invitaciones en bbdd
        List<UserInvitation> invitations = userInvitationWeddingRepository.findByUserAppId(dto.getUserId());

        for (UserInvitation ui : invitations){
            System.out.println(ui.isConfirm());
        }
        for (UserInvitationDTO ui : dto.getInvitationList()){
            System.out.println(ui.isConfirm());
        }

        for(UserInvitation userInvitation : invitations){

            for (UserInvitationDTO userInvitationDTO : dto.getInvitationList()){

                if (userInvitation.getId().equals(userInvitationDTO.getIdInvitation())){

                    userInvitation.setConfirm(userInvitationDTO.isConfirm());
                    userInvitation.setNotified(userInvitationDTO.isNotified());

                    updateListCompanion(userInvitation, userInvitationDTO);

                    userInvitationWeddingRepository.save(userInvitation);

                    break;
                }
            }
        }

        return userAppMapper.toListUserInvitation(userRepository.findById(dto.getUserId()).orElseThrow());

    }

    private void updateListCompanion(UserInvitation invitationWedding, UserInvitationDTO userInvitationDTO){

        // bbdd
        List<Companion> companions = invitationWedding.getCompanions();
        // no en dto
        List<Companion> toRemove = new ArrayList<>();


        for (Companion companion : companions){

            boolean found = false;

            for (UserCompanionDTO userCompanionDTO : userInvitationDTO.getCompanions()){

                // si esta en ambas listas -> update
                if (companion.getId().equals(userCompanionDTO.getIdCompanion())){

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

            if(!found) {
                toRemove.add(companion);
            }

        }

        for (UserCompanionDTO userCompanionDTO : userInvitationDTO.getCompanions()){

            if (userCompanionDTO.getIdCompanion()==null){

                Companion companion = updateCompanion(invitationWedding, userCompanionDTO);

                companions.add(companion);

            }
        }

        companions.removeAll(toRemove);

    }

    private Companion updateCompanion(UserInvitation invitationWedding, UserCompanionDTO userCompanionDTO) {

        Companion companion = new Companion();

        companion.setName(userCompanionDTO.getName());
        companion.setFirstSurname(userCompanionDTO.getFirstSurname());
        companion.setSecondSurname(userCompanionDTO.getSecondSurname());
        companion.setEmail(userCompanionDTO.getEmail());
        companion.setAdult(userCompanionDTO.isAdult());
        companion.setAllergies(userCompanionDTO.getAllergies());

        companion.setUserInvitationWedding(invitationWedding);

        return companion;
    }


    @Override
    public ListUserPresentDTO updateListUserPresent(ListUserPresentDTO dto) {

        UserApp user = userRepository.findById(dto.getUserId()).orElseThrow();

        List<Present> presentsBD = presentRepository.findByUserAppId(dto.getUserId());

        for (Present present : user.getPresents()){
            for (UserPresentDTO userPresentDTO : dto.getUserPresents()){
                if(present.getId().equals(userPresentDTO.getIdPresent())){
                    present.setConfirm(userPresentDTO.isConfirm());
                    break;
                }
            }
        }

        presentRepository.saveAll(presentsBD);

        return getListUserPresent(user.getId());
    }

    @Override
    public UserApp save(UserApp entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<UserApp> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
