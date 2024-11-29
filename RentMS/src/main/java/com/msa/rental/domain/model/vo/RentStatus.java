package com.msa.rental.domain.model.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RentStatus {

    RENT_AVAILABLE("대여 가능"),
    RENT_UNAVAILABLE("대여 불가능");

    private final String message;
}
