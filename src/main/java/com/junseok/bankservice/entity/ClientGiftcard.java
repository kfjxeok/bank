package com.junseok.bankservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClientGiftcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "giftcard_id")
    private GiftcardEntity giftcard;

    private int quantity; // 고객이 보유한 기프트카드 수량을 저장
}
