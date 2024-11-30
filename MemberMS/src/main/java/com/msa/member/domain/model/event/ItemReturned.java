package com.msa.member.domain.model.event;

import com.msa.member.domain.model.vo.IDName;
import lombok.Getter;

@Getter
public class ItemReturned extends ItemRented {

    public ItemReturned(IDName idName, Item item, long point) {
        super(idName, item, point);
    }
}
