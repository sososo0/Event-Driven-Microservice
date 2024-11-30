package com.msa.member.domain.model.event;

import com.msa.member.domain.model.vo.IDName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PointUseCommand implements Serializable {
    private IDName idName;
    private long point;
}
