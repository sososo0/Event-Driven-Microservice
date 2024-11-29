package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.UserItemInputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;

public interface ReturnItemUseCase {
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws Exception;
}
