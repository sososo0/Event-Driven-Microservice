package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.ClearOverdueInfoDTO;
import com.msa.rental.framework.web.dto.RentalResultOutputDTO;

public interface ClearOverdueItemUseCase {
    public RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception;
}
