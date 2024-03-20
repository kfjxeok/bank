package com.junseok.bankservice.controller;

import com.junseok.bankservice.dto.DepositRequestDto;
import com.junseok.bankservice.service.DepostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class DepositController {
    @Autowired
    private DepostService depostService;

    @PostMapping("/account")
    public String Deposit(@RequestBody DepositRequestDto depositRequestDto ){
        depostService.Deposit(depositRequestDto);
        return "성공";
    }

}
