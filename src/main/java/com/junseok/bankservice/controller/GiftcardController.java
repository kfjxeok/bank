package com.junseok.bankservice.controller;

import com.junseok.bankservice.dto.BuyGiftcardDTO;
import com.junseok.bankservice.dto.CreateGiftcardDTO;
import com.junseok.bankservice.entity.GiftcardEntity;
import com.junseok.bankservice.service.GiftcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftcard" +
        "")

public class GiftcardController {
    @Autowired
    private GiftcardService giftcardService;
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_MASTER')")
    public String createGiftcard(@RequestBody CreateGiftcardDTO createGiftcardDTO)
    {
        return giftcardService.CreateGiftcard(createGiftcardDTO);
    }
    @GetMapping
    public List<GiftcardEntity> getGiftcardlist()
    {
        return giftcardService.getGiftcardlist();
    }
    @PostMapping("/buy")
    public String buyGiftcard(@RequestBody BuyGiftcardDTO buyGiftcardDTO)
    {
        return giftcardService.BuyGiftcard(buyGiftcardDTO);
    }
}


