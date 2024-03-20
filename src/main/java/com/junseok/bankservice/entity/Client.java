package com.junseok.bankservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    private String clientName;
    private int clientBirth;
    private  int residentNum;
    private int phoneNum;
    private  int clientAddress;
    private  String clientEmail;
    private  int clientJob;
    private String loginId;
    private String passWord;

    @OneToMany(mappedBy = "client")
    private List<DepositAccount> depositAccounts = new ArrayList<>();
    public Client(String clientName, String clientEmail, String passWord, int phoneNum){
        this.clientName=clientName;
        this.clientEmail=clientEmail;
        this.passWord=passWord;
        this.phoneNum=phoneNum;


    }

    public Client() {

    }
}