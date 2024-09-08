package com.junseok.bankservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MypageDTO {
    private String phone_num;
    private String email;
    private String name;
    private String loginId;
    private double point;
    private List<GiftcardInfoDTO> giftcards; // 기프트카드 목록 추가

    @Getter
    @Setter
    public static class GiftcardInfoDTO {
        private String name;
        private int quantity;
        private int point;
    }
}
