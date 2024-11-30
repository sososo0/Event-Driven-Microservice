package com.msa.rental.application.usecase;

import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;

public interface CompensationUseCase {
    public RentalCard cancelRentItem(IDName idName, Item item);
    public RentalCard cancelReturnItem(IDName idName, Item item, long point) throws Exception;
    public long cancelMakeAvailableRental(IDName idName, long point);
}
