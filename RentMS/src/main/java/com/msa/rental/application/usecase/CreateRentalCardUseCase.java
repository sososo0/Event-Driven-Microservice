package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.UserInputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;

public interface CreateRentalCardUseCase {
    public RentalCardOutputDTO createRentalCard(UserInputDTO userInputDTO);
}
