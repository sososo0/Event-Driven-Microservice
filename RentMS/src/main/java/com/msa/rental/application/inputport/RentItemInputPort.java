package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.RentItemUseCase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentItemInputPort implements RentItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO rentItem(UserItemInputDTO rental) throws Exception {
        // 대여는 카드가 없으면 만든다.
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rental.getUserId())
            .orElseGet(() -> RentalCard.createRentalCard(new IDName(rental.getUserId(), rental.getUsername())));

        Item newItem = new Item(rental.getItemId(), rental.getItemTitle());
        rentalCard.rentItem(newItem);
        RentalCard savedRentalCard = rentalCardOutputPort.save(rentalCard);
        return RentalCardOutputDTO.mapToDTO(savedRentalCard);
    }
}
