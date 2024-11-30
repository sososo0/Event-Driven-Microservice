package com.msa.rental.domain.model.event;

import com.msa.rental.domain.model.vo.IDName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PointUseCommand implements Serializable {
    private IDName idName;
    private long point;
}
