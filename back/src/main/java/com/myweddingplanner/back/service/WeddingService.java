package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.wedding.ListEmailInvitation;
import com.myweddingplanner.back.dto.wedding.ListWeddingInvitationDTO;
import com.myweddingplanner.back.dto.wedding.ListWeddingPresentDTO;
import com.myweddingplanner.back.dto.wedding.WeddingDTO;
import com.myweddingplanner.back.model.Wedding;
import java.util.Optional;

public interface WeddingService {

    Optional<WeddingDTO> getById(Long id);

    Optional<WeddingDTO> getWeddingPreparingByUserId(Long userId);

    ListWeddingPresentDTO getListWeddingPresent (Long weddingId);

    ListWeddingInvitationDTO getListWeddingInvitation(Long weddingId);

    WeddingDTO updateWeddingDTO (WeddingDTO dto);

    ListWeddingPresentDTO updateListWeddingPresent (ListWeddingPresentDTO dto);

    ListWeddingInvitationDTO addInvitation(ListEmailInvitation toAdd);

    Wedding save(Wedding wedding);

}
