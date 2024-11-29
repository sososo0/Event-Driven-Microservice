package com.msa.rental.domain.model.vo;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class IDName {
    private String id;
    private String name;

    public static IDName sample() {
        return new IDName("scant", "han");
    }

    public static void main(String[] args) {
        System.out.println(sample().toString());
    }
}
