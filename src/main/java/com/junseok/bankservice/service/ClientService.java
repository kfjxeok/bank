package com.junseok.bankservice.service;

import com.junseok.bankservice.dto.MypageDTO;
import com.junseok.bankservice.entity.Client;
import com.junseok.bankservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public MypageDTO getUserInfo(String clientEmail){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userId);
        /*Optional<Client> client = clientRepository.findById(u);
        MypageDTO mypageDTO = new MypageDTO();
        mypageDTO.setName(client.getClientName());
        mypageDTO.setEmail(client.getClientEmail());
        mypageDTO.setPhone_num(client.getPhoneNum());
        mypageDTO.setLoginId(client.getLoginId());
        mypageDTO.setPoint(client.getPoint());*/
        return null;
    }
}
