package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.UserItemInputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;

public interface RentItemUseCase {
    public RentalCardOutputDTO rentItem(UserItemInputDTO rental) throws Exception;
}
