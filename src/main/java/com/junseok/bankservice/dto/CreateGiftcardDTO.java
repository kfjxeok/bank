package com.junseok.bankservice.dto;

import lombok.Getter;

@Getter
public class CreateGiftcardDTO {
    String name;
    int amount;
    int point;
    int count;

}
