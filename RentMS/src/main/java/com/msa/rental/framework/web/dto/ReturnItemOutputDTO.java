package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.vo.ReturnItem;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnItemOutputDTO {

    private Integer itemNo;
    private String itemTitle;
    private LocalDate returnDate;

    public static ReturnItemOutputDTO mapToDTO(ReturnItem returnItem) {
        ReturnItemOutputDTO returnItemOutputDTO = new ReturnItemOutputDTO();
        returnItemOutputDTO.setItemNo(returnItem.getRentalItem().getItem().getNo());
        returnItemOutputDTO.setItemTitle(returnItem.getRentalItem().getItem().getTitle());
        returnItemOutputDTO.setReturnDate(returnItem.getActualReturnDate());
        return returnItemOutputDTO;
    }
}
