package com.junseok.bankservice.service;

import com.junseok.bankservice.dto.CreateGiftcardDTO;
import com.junseok.bankservice.entity.GiftcardEntity;
import com.junseok.bankservice.repository.ClientRepository;
import com.junseok.bankservice.repository.GiftcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiftcardService {
    @Autowired
    private GiftcardRepository giftcardRepository;

    public String CreateGiftcard(CreateGiftcardDTO createGiftcardDTO){
        GiftcardEntity giftcardEntity=new GiftcardEntity();
        giftcardEntity.setAmount(createGiftcardDTO.getAmount());
        giftcardEntity.setName(createGiftcardDTO.getName());
        giftcardEntity.setPoint(createGiftcardDTO.getPoint());
        giftcardRepository.save(giftcardEntity);
        return "성공";
    }

}
