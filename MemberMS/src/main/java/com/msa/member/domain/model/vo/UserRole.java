package com.msa.member.domain.model.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRole {
    ADMIN("관리자"),
    USER("일반 사용자");

    private final String message;
}
