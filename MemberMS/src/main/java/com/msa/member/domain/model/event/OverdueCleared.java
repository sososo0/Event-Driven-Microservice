package com.msa.member.domain.model.event;

import com.msa.member.domain.model.vo.IDName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OverdueCleared implements Serializable {

    private IDName idName;
    private long point;
}
