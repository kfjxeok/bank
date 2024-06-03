package com.junseok.bankservice.controller;

import com.junseok.bankservice.dto.MypageDTO;
import com.junseok.bankservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{userId}")
    public MypageDTO getUserInfo(@PathVariable int userId){
        return clientService.getUserInfo(userId);
    }

}
