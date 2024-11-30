package com.msa.member.domain.model.event;

import com.msa.member.domain.model.vo.IDName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventResult implements Serializable {
    private BookEventType eventType;
    private boolean isSuccessful;
    private IDName idName;
    private Item item;
    private long point;
}
