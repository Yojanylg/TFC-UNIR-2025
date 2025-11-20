package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.users.*;
import com.myweddingplanner.back.exception.UserNotFoundException;
import com.myweddingplanner.back.mapper.UserAppMapper;
import com.myweddingplanner.back.model.Companion;
import com.myweddingplanner.back.model.UserApp;
import com.myweddingplanner.back.model.UserInvitationWedding;
import com.myweddingplanner.back.repository.UserAppRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAppServiceImpl implements UserAppService{

    private final UserAppRepository userRepository;
    private final UserAppMapper userAppMapper;

    public UserAppServiceImpl(UserAppRepository userRepository, UserAppMapper userAppMapper) {
        this.userRepository = userRepository;
        this.userAppMapper = userAppMapper;
    }

    @Override
    public MyUserDTO findMyUserDTOById(Long id) {

        return userRepository.findById(id)
                .map(userAppMapper::toMyUserDTO)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public MyUserAllergiesDTO findMyUserAllergiesDTOById(Long id) {

        return userRepository.findById(id)
                .map(userAppMapper::toMyUserAllergiesDTO)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public MyUserPresentDTO finMyUserPresentDTOById(Long id) {
        return userRepository.findById(id)
                .map(userAppMapper::toMyUserPresentDTO)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public MyUserDTO update(MyUserDTO dto) {

        UserApp user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new UserNotFoundException(dto.getIdUser()));

        // todo comprobar si hay cambios y actualizar

        updateInvitations(user, dto);

        return userAppMapper.toMyUserDTO( userRepository.save(user));
    }

    private void updateInvitations(UserApp user, MyUserDTO dto){

        // lo que hay en bbdd
        List<UserInvitationWedding> invitations = user.getInvitations();
        // lo que no hay en dto
        List<UserInvitationWedding> toRemove = new ArrayList<>();

        for (UserInvitationWedding invitationWedding : invitations){

            MyInvitation myInvitation = findMyInvitation(dto, invitationWedding.getId());

            if (myInvitation == null){
                toRemove.add(invitationWedding);
            } else {
                updateInvitation(invitationWedding, myInvitation);
            }

        }

        invitations.removeAll(toRemove);
    }

    private MyInvitation findMyInvitation(MyUserDTO dto, Long invitationId){

        if (dto.getMyInvitations() == null){

            return null;

        }

        for (MyInvitation myInvitation : dto.getMyInvitations()){

            if (invitationId.equals(myInvitation.getIdInvitation())){

                return myInvitation;

            }

        }

        return null;

    }

    private void updateInvitation(UserInvitationWedding invitationWedding, MyInvitation myInvitation){

        invitationWedding.setConfirm(myInvitation.isConfirm());
        invitationWedding.setNotified(myInvitation.isNotified());

        updateCompanions(invitationWedding, myInvitation);

    }

    private void updateCompanions(UserInvitationWedding invitationWedding, MyInvitation myInvitation){

        // bbdd
        List<Companion> companions = invitationWedding.getCompanions();
        // no en dto
        List<Companion> toRemove = new ArrayList<>();

        if (companions == null){

            return;

        }

        for (Companion companion : companions){

            MyCompanion myCompanion = findMyCompanion(myInvitation, companion.getId());


            if (myCompanion == null){

                toRemove.add(companion);

            } else {

                updateCompanion(companion, myCompanion);
            }

        }

        companions.removeAll(toRemove);

    }

    private MyCompanion findMyCompanion(MyInvitation myInvitation, Long companionId){

        if (myInvitation.getCompanions() == null){
            return null;
        }

        for (MyCompanion myCompanion : myInvitation.getCompanions()){

            if (companionId.equals(myCompanion.getIdCompanion())){

                return myCompanion;
            }
        }

        return null;
    }

    private void updateCompanion(Companion companion, MyCompanion myCompanion){

        companion.setName(myCompanion.getName());
        companion.setFirstSurname(myCompanion.getFirstSurname());
        companion.setSecondSurname(myCompanion.getSecondSurname());
        companion.setEmail(myCompanion.getEmail());
        companion.setAdult(myCompanion.isAdult());
        companion.setAllergies(myCompanion.getAllergies());

    }





    // TODO

    @Override
    public List<UserApp> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserApp save(UserApp entity) {
        return userRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
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
