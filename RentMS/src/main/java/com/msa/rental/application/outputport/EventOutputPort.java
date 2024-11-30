package com.msa.rental.application.outputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.msa.rental.domain.model.event.ItemRented;
import com.msa.rental.domain.model.event.ItemReturned;
import com.msa.rental.domain.model.event.OverdueCleared;
import org.springframework.stereotype.Repository;

@Repository
public interface EventOutputPort {
    public void occurRentalEvent(ItemRented rentalItem) throws JsonProcessingException;
    public void occurReturnEvent(ItemReturned returnItem) throws JsonProcessingException;
    public void occurOverdueClearedEvent(OverdueCleared overdueCleared) throws JsonProcessingException;
}
