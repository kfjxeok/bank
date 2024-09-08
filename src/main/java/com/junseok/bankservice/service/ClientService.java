package com.junseok.bankservice.service;

import com.junseok.bankservice.dto.MypageDTO;
import com.junseok.bankservice.entity.Client;
import com.junseok.bankservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public MypageDTO getUserInfo(String clientEmail){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userId);
        Optional<Client> client1 = clientRepository.findByClientEmail(userId);
        Client client = client1.get();

        MypageDTO mypageDTO = new MypageDTO();
        mypageDTO.setName(client.getClientName());
        mypageDTO.setEmail(client.getClientEmail());
        mypageDTO.setPhone_num(client.getPhoneNum());
        mypageDTO.setLoginId(client.getLoginId());
        mypageDTO.setPoint(client.getPoint());


        List<MypageDTO.GiftcardInfoDTO> giftcardInfoDTOS = client.getClientGiftcards().stream().map(giftcard -> {
            MypageDTO.GiftcardInfoDTO giftcardInfoDTO = new MypageDTO.GiftcardInfoDTO();
            giftcardInfoDTO.setName(giftcard.getGiftcard().getName());
            giftcardInfoDTO.setQuantity(giftcard.getQuantity());
            giftcardInfoDTO.setPoint(giftcard.getGiftcard().getPoint());
            return giftcardInfoDTO;
        }).collect(Collectors.toList());

        mypageDTO.setGiftcards(giftcardInfoDTOS);

        return mypageDTO;
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
