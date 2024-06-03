package com.junseok.bankservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
    private String phoneNum;
    private  int clientAddress;
    private  String clientEmail;
    private String loginId;
    private String passWord;
    private Authority authority;
    private double point;

    @OneToMany(mappedBy = "client")
    private List<DepositAccount> depositAccounts = new ArrayList<>();
    public Client(String clientName, String clientEmail, String passWord, String phoneNum){
        this.clientName=clientName;
        this.clientEmail=clientEmail;
        this.passWord=passWord;
        this.phoneNum=phoneNum;


    }

    public Client() {

    }
}
