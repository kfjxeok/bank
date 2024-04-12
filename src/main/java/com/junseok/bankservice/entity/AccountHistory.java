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
public class AccountHistory {
    @Id
    private int account_payment;

    private String account_payment_marchent;
    private int account_income_history;
    private int account_output_history;
    private String account_payment_name;
    private String account_income_name;
    private int account_payment_time;
    private String account_income_time;

    @ManyToOne
    @JoinColumn(name = "deposit_account_id")
    private DepositAccount depositAccount ;


}
