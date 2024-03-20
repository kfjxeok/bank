package com.junseok.bankservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    private String clientName;
    private String clientEmail;
    private String passWord;
    private int phoneNum;
    private String accountNum;
}
