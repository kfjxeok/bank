package com.junseok.bankservice.dto;

import lombok.Getter;

@Getter
public class CreateGiftcardDTO {
    int id;
    String name;
    int amount;
    int point;

}
