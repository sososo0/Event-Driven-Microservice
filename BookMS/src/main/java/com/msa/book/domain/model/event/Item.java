package com.msa.book.domain.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Long no;
    private String title;

    public static Item sample() {
        return new Item(10L, "노인과 바다");
    }
}
