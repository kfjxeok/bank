package com.junseok.bankservice.controller;

import com.junseok.bankservice.dto.CreateGiftcardDTO;
import com.junseok.bankservice.service.GiftcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/giftcard")

public class GiftcardController {
    @Autowired
    private GiftcardService giftcardService;
    @PostMapping
    public String createGiftcard(@RequestBody CreateGiftcardDTO createGiftcardDTO)
    {
        return giftcardService.CreateGiftcard(createGiftcardDTO);
    }
}
