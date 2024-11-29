package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.ReturnItemUseCase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReturnItemInputPort implements ReturnItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDTO) throws Exception {
        // 반납은 반드시 카드가 있어야 한다.
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(returnDTO.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));

        Item returnItem = new Item(returnDTO.getItemId(), returnDTO.getItemTitle());
        rentalCard.returnItem(returnItem, LocalDate.now());
        // 변경 감지에 의해 save는 사용하지 않음
        return RentalCardOutputDTO.mapToDTO(rentalCard);
    }
}
