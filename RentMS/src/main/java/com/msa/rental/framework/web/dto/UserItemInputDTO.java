package com.msa.rental.framework.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserItemInputDTO {
    public String userId;
    public String username;
    public Integer itemId;
    public String itemTitle;
}
