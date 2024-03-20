package com.junseok.bankservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity

public class Card {
    @Id
    private int cardId;

    private String card_type;
    private Date card_regi_date;
    private int card_pay_date;
    private int card_limit_amount;
    private int card_pay_amount;
    private int card_password;
    private boolean card_trans;
    private int cvc_number;
    private Date expiration_date;

    @ManyToOne
    @JoinColumn(name = "deposit_account")
    private DepositAccount depositAccount ;

    @OneToMany(mappedBy = "card_history")
    private List<CardHistory> cardHistories = new ArrayList<>();
}
