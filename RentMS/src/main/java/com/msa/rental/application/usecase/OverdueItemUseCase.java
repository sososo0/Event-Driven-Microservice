package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.UserItemInputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;

public interface OverdueItemUseCase {
    public RentalCardOutputDTO overDueItem(UserItemInputDTO rental) throws Exception;
}
