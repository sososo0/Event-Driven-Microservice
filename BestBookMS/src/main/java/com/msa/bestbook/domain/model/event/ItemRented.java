package com.msa.bestbook.domain.model.event;

import com.msa.bestbook.domain.model.vo.BookItem;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ItemRented implements Serializable {
    private IDName idName;
    private BookItem item;
    private long point;
}