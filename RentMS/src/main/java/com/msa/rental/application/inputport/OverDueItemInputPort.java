package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.OverdueItemUseCase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OverDueItemInputPort implements OverdueItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO overDueItem(UserItemInputDTO rental) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rental.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));

        rentalCard.overdueItem(new Item(rental.getItemId(), rental.getItemTitle()));
        return RentalCardOutputDTO.mapToDTO(rentalCard);
    }
}
