package com.msa.member.domain.model.event;

import com.msa.member.domain.model.vo.IDName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemRented implements Serializable {

    private IDName idName;
    private Item item;
    private long point;
}
