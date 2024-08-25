package com.junseok.bankservice.service;

import com.junseok.bankservice.dto.BuyGiftcardDTO;
import com.junseok.bankservice.dto.CreateGiftcardDTO;
import com.junseok.bankservice.entity.Client;
import com.junseok.bankservice.entity.GiftcardEntity;
import com.junseok.bankservice.repository.ClientRepository;
import com.junseok.bankservice.repository.GiftcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftcardService {
    @Autowired
    private GiftcardRepository giftcardRepository;
    @Autowired
    private  ClientRepository clientRepository;




    public String CreateGiftcard(CreateGiftcardDTO createGiftcardDTO){
        GiftcardEntity giftcardEntity=new GiftcardEntity();
        giftcardEntity.setAmount(createGiftcardDTO.getAmount());
        giftcardEntity.setName(createGiftcardDTO.getName());
        giftcardEntity.setPoint(createGiftcardDTO.getPoint());
        giftcardEntity.setCount(createGiftcardDTO.getCount());
        giftcardRepository.save(giftcardEntity);
        return "성공";
    }
    public List<GiftcardEntity> getGiftcardlist()
    {
        List<GiftcardEntity> giftcardEntityList=giftcardRepository.findAll();
        return giftcardEntityList;
    }

    public String BuyGiftcard(BuyGiftcardDTO buyGiftcardDTO){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByClientEmail(name).orElseThrow();

        GiftcardEntity giftcardEntity = giftcardRepository.findByName(buyGiftcardDTO.getName());
        int point = giftcardEntity.getPoint();

        if(client.getPoint() >= point )
        {

        }
        else
        {
            return "포인트 부족";
        }
    }
}
