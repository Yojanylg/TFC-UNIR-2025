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

        List<UserInvitationWedding> invitations = userInvitationWeddingRepository.findByUserAppId(userId);

        for (UserInvitationWedding invitation : invitations){
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
        user.setAllergies(dto.getUserAllergies());

        return userAppMapper.toMyUserDTO(userRepository.save(user));
    }

    @Override
    public ListUserInvitationDTO updateListUserInvitation(ListUserInvitationDTO dto) {

        Long userId = dto.getUserId();

        UserApp user = userRepository.findById(userId).orElseThrow();

        // el usuario solo puede cambiar el estado de sus invitaciones
        // no puede add ni delete

        // invitaciones en bbdd
        List<UserInvitationWedding> invitations = userInvitationWeddingRepository.findByUserAppId(dto.getUserId());

        for(UserInvitationWedding userInvitationWedding : invitations){

            for (MyInvitation myInvitation : dto.getInvitationList()){

                if (userInvitationWedding.getId().equals(myInvitation.getIdInvitation())){

                    userInvitationWedding.setConfirm(myInvitation.isConfirm());
                    userInvitationWedding.setNotified(myInvitation.isNotified());

                    updateListCompanion(userInvitationWedding, myInvitation);

                    userInvitationWeddingRepository.save(userInvitationWedding);

                    break;
                }
            }
        }

        return userAppMapper.toListUserInvitation(userRepository.findById(dto.getUserId()).orElseThrow());

    }

    private void updateListCompanion(UserInvitationWedding invitationWedding, MyInvitation myInvitation){

        // bbdd
        List<Companion> companions = invitationWedding.getCompanions();
        // no en dto
        List<Companion> toRemove = new ArrayList<>();


        for (Companion companion : companions){

            boolean found = false;

            for (MyCompanion myCompanion : myInvitation.getCompanions()){

                // si esta en ambas listas -> update
                if (companion.getId().equals(myCompanion.getIdCompanion())){

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

            if(!found) {
                toRemove.add(companion);
            }

        }

        for (MyCompanion myCompanion : myInvitation.getCompanions()){

            if (myCompanion.getIdCompanion()==null){

                Companion companion = updateCompanion(invitationWedding, myCompanion);

                companions.add(companion);

            }
        }

        companions.removeAll(toRemove);

    }

    private Companion updateCompanion(UserInvitationWedding invitationWedding, MyCompanion myCompanion) {

        Companion companion = new Companion();

        companion.setName(myCompanion.getName());
        companion.setFirstSurname(myCompanion.getFirstSurname());
        companion.setSecondSurname(myCompanion.getSecondSurname());
        companion.setEmail(myCompanion.getEmail());
        companion.setAdult(myCompanion.isAdult());
        companion.setAllergies(myCompanion.getAllergies());

        companion.setUserInvitationWedding(invitationWedding);

        return companion;
    }


    @Override
    public ListUserPresentDTO updateListUserPresent(ListUserPresentDTO dto) {

        List<Present> presentsBD = presentRepository.findByUserAppId(dto.getUserId());

        for (Present present : presentsBD){
            for (MyPresent myPresent : dto.getMyPresents()){
                if(present.getId().equals(myPresent.getIdPresent())){
                    present.setConfirm(myPresent.isConfirm());
                    break;
                }
            }
        }

        List<Present> update = presentRepository.saveAll(presentsBD);

        return userAppMapper.toListUserPresent(userRepository.findById(dto.getUserId()).orElseThrow());
    }

    public boolean updateListUserPresents(Long userId, ListUserPresentDTO dto){

        UserApp user = userRepository.findById(userId).orElseThrow();
        List<Present> actual = user.getPresents();
        List<MyPresent> enDTO = dto.getMyPresents();

        // eliminar los que han pasado a no confirmados
        for (MyPresent mp : enDTO){
            if (!mp.isConfirm()){
                for (Present present : user.getPresents()){
                    if (present.getId().equals(mp.getIdPresent())){
                        present.setUserApp(null);
                        present.setConfirm(false);
                        presentRepository.save(present);
                    }
                }
            }
        }

        return true;
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
