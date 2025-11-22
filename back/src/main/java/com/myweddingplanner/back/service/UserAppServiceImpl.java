package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.users.*;
import com.myweddingplanner.back.exception.UserNotFoundException;
import com.myweddingplanner.back.mapper.UserAppMapper;
import com.myweddingplanner.back.model.*;
import com.myweddingplanner.back.repository.AllergenRepository;
import com.myweddingplanner.back.repository.PresentRepository;
import com.myweddingplanner.back.repository.UserAppRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAppServiceImpl implements UserAppService{

    private final UserAppRepository userRepository;
    private final PresentRepository presentRepository;
    private final AllergenRepository allergenRepository;
    private final UserAppMapper userAppMapper;

    public UserAppServiceImpl(UserAppRepository userRepository, PresentRepository presentRepository, AllergenRepository allergenRepository, UserAppMapper userAppMapper) {
        this.userRepository = userRepository;
        this.presentRepository = presentRepository;
        this.allergenRepository = allergenRepository;
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

    @Override
    public boolean updateUserAllergies(Long userId, MyUserAllergiesDTO dto) {

        UserApp user = userRepository.findById(userId).get();

        List<AllergiesUser> toRemove = new ArrayList<>();

        // para cada alergia que hay en la lista de alergias del usuario en la bb
        // lo comparo con cada una de las alergias que hay en el dto
        // si coincide con alguna es que sigue estando
        // si no coincide con ninguna la añado a la lista de eliminar
        // porque está en la bbdd pero no en el dto
        for (AllergiesUser allergie : user.getAllergies()){

            boolean found = false;

            for (MyAllergy myAllergy : dto.getMyAllergies()){

                if (allergie.getId().equals(myAllergy.getIdAllergy())){
                    found = true;
                    break;
                }
            }

            if(!found){

                toRemove.add(allergie);
            }
        }

        user.getAllergies().removeAll(toRemove);

        // Agrego lo nuevo en el dto a la bbdd
        // si el id es null es que es nuevo
        for (MyAllergy myAllergy : dto.getMyAllergies()){
            if (myAllergy.getIdAllergy() == null){

                AllergiesUser allergie = new AllergiesUser();

                allergie.setUserApp(user);
                allergie.setAllergen(allergenRepository.findById(myAllergy.getIdAllergen()).get());

                user.getAllergies().add(allergie);
            }
        }

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean updateMyUserPresents(Long userId, MyUserPresentDTO dto){

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
