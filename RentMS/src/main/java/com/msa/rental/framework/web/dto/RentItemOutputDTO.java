package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.RentalItem;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentItemOutputDTO {

    private Integer itemNo;
    private String itemTitle;

    private LocalDate rentDate;
    private boolean overdued;

    // 반납 예정일
    private LocalDate returnDate;

    public static RentItemOutputDTO mapToDTO(RentalItem rentalItem) {
        RentItemOutputDTO rentItemOutputDTO = new RentItemOutputDTO();
        rentItemOutputDTO.setItemNo(rentalItem.getItem().getNo());
        rentItemOutputDTO.setItemTitle(rentalItem.getItem().getTitle());
        rentItemOutputDTO.setRentDate(rentalItem.getRentDate());
        rentItemOutputDTO.setOverdued(rentalItem.isOverdued());
        rentItemOutputDTO.setRentDate(rentalItem.getRentDate());
        return rentItemOutputDTO;
    }
}
