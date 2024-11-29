package com.msa.book.domain.model.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookLocation {
    JEONGJA("정자"),
    PANGYO("판교");

    private final String message;
}
