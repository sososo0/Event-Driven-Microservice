package com.msa.book.domain.model.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Classification {
    ARTS("예술"),
    COMPUTER("컴퓨터"),
    LITERATURE("문학");

    private final String message;
}
