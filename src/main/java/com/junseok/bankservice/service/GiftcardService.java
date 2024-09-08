package com.junseok.bankservice.service;

import com.junseok.bankservice.dto.BuyGiftcardDTO;
import com.junseok.bankservice.dto.CreateGiftcardDTO;
import com.junseok.bankservice.entity.Client;
import com.junseok.bankservice.entity.ClientGiftcard;
import com.junseok.bankservice.entity.GiftcardEntity;
import com.junseok.bankservice.repository.ClientGiftcardRepository;
import com.junseok.bankservice.repository.ClientRepository;
import com.junseok.bankservice.repository.GiftcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GiftcardService {
    @Autowired
    private GiftcardRepository giftcardRepository;
    @Autowired
    private  ClientRepository clientRepository;
    @Autowired
    private ClientGiftcardRepository clientGiftcardRepository;




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


    public String BuyGiftcard(BuyGiftcardDTO buyGiftcardDTO) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Client client = clientRepository.findByClientEmail(name).orElseThrow(() -> new RuntimeException("Client not found"));


        GiftcardEntity giftcardEntity = giftcardRepository.findByName(buyGiftcardDTO.getName());

        int point = giftcardEntity.getPoint() * buyGiftcardDTO.getCount();


        if (client.getPoint() >= point) {
            client.setPoint(client.getPoint() - point);
            Optional<ClientGiftcard> existingClientGiftcard = client.getClientGiftcards().stream(

                    )
                    .filter(cg -> cg.getGiftcard().equals(giftcardEntity))
                    .findFirst();

            if (existingClientGiftcard.isPresent()) {
                ClientGiftcard clientGiftcard = existingClientGiftcard.get();
                clientGiftcard.setQuantity(clientGiftcard.getQuantity() + buyGiftcardDTO.getCount());
            } else {
                ClientGiftcard clientGiftcard = new ClientGiftcard();
                clientGiftcard.setClient(client);
                clientGiftcard.setGiftcard(giftcardEntity);
                clientGiftcard.setQuantity(buyGiftcardDTO.getCount());
                client.getClientGiftcards().add(clientGiftcard);
            }

            clientRepository.save(client);

            return "구매 성공";
        }
        else if(buyGiftcardDTO.getCount() ==0){
            return "수량이 0개 입니다.";
        }else {
            return "포인트 부족";
        }
    }

    public boolean doesClientOwnGiftcard(String clientEmail, String giftcardName) {
        // 클라이언트 정보를 가져옴
        Client client = clientRepository.findByClientEmail(clientEmail)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // 클라이언트가 소유한 기프트카드 목록에서 확인
        return client.getClientGiftcards().stream()
                .anyMatch(clientGiftcard -> clientGiftcard.getGiftcard().getName().equals(giftcardName));
    }
}
