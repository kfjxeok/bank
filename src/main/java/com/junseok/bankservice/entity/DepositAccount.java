package com.junseok.bankservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class DepositAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int depositId;

    private String accountType;
    private String depositBalance;
    private Date depositRegiDate;
    private String depositName;
    private String cardStatus;
    private int depositPassword;
    private int datTransferLimit;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "card")
    private List<Card> card = new ArrayList<>();

    @OneToMany(mappedBy = "account_history")
    private List<AccountHistory> accountHistories = new ArrayList<>();

}
