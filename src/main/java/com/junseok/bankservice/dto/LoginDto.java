package com.junseok.bankservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String clientEmail;
    private String passWord;
}
