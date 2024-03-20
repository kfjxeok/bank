package com.junseok.bankservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositRequestDto {
    private String accountNum;
    private int amount;
    private int id;
}
