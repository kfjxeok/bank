package com.junseok.bankservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MypageDTO {
    //phone_num, email, name
    private String phone_num;
    private String email;
    private String name;
    private String loginId;
    private double point;
}
