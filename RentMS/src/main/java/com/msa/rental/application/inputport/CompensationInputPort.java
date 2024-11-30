package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.EventOutputPort;
import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.CompensationUseCase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.event.PointUseCommand;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompensationInputPort implements CompensationUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCard cancelRentItem(IDName idName, Item item) {
        return rentalCardOutputPort.loadRentalCard(idName.getId())
            .map(rentalCard -> {
                try {
                    rentalCard.cancelRentItem(item);
                    eventOutputPort.occurPointUseCommand(new PointUseCommand(idName, 10L));
                    return rentalCard;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .orElseThrow(() -> new NoSuchElementException("Rental Card Not Found For ID: " + idName.getId()));
    }

    @Override
    public RentalCard cancelReturnItem(IDName idName, Item item, long point) throws Exception {
        return rentalCardOutputPort.loadRentalCard(idName.getId())
            .map(rentalCard -> {
                try {
                    rentalCard.cancelReturnItem(item);
                    eventOutputPort.occurPointUseCommand(new PointUseCommand(idName, point));
                    return rentalCard;
                } catch(Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .orElseThrow(() -> new NoSuchElementException("Rental Card Not Found For ID: " + idName.getId()));
    }

    @Override
    public long cancelMakeAvailableRental(IDName idName, long point) {
        return rentalCardOutputPort.loadRentalCard(idName.getId())
            .map(rentalCard -> {
                try {
                    return rentalCard.cancelMakeAvailableRental(point);
                } catch(Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .orElseThrow(() -> new NoSuchElementException("Rental Card Not Found For ID: " + idName.getId()));
    }
}
