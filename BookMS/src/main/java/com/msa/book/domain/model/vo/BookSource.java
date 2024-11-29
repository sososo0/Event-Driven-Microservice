package com.msa.book.domain.model.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookSource {
    DONATION("기부"),
    SUPPLY("공급");

    private final String message;
}
