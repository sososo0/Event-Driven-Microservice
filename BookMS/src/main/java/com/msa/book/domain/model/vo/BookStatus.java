package com.msa.book.domain.model.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookStatus {
    ENTERED("입고"),
    AVAILABLE("이용가능"),
    UNAVAILABLE("불가능");

    private final String message;
}
