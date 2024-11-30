package com.msa.book.domain.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IDName {

    private String id;
    private String name;

    public static IDName sample() {
        return new IDName("scant", "han");
    }
}
