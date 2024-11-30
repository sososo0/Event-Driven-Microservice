package com.msa.rental.domain.model.event;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookEventType {
    RENT("대여"),
    RETURN("반납"),
    OVERDUE("연체");

    private final String message;
}
