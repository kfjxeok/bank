package com.junseok.bankservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CardHistory {
    @Id
    private int card_payment;

    private String card_payment_marchent;
    private String card_used;

    @ManyToOne
    @JoinColumn(name = "card")
    private Card card ;
}
