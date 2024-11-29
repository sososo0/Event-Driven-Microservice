package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.CreateRentalCardUseCase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: 왜 interface로 선언하지 않을까?
@Service
@Transactional
@RequiredArgsConstructor
public class CreateRentalCardInputPort implements CreateRentalCardUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO createRentalCard(UserInputDTO owner) {
        RentalCard rentalCard = RentalCard.createRentalCard(new IDName(owner.getUserId(), owner.getUserName()));
        RentalCard savedRentalCard = rentalCardOutputPort.save(rentalCard);
        return RentalCardOutputDTO.mapToDTO(savedRentalCard);
    }
}
