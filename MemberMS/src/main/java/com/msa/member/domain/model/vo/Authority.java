package com.msa.member.domain.model.vo;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Authority {

    public UserRole roleName;

    public static Authority sample() {
        return new Authority(UserRole.USER);
    }
}
