package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.Item;
import java.time.LocalDate;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RentalItem {

    @Embedded
    private Item item;

    private LocalDate rentDate; // 대여일

    private boolean overdued; // 연체 여부

    private LocalDate expectedReturnDate; // 반납 예정일

    public static RentalItem createRentalItem(Item item) {
        return new RentalItem(item, LocalDate.now(), false, LocalDate.now().plusDays(14));
    }

    public static RentalItem sample() {
        return RentalItem.createRentalItem(Item.sample());
    }
}
