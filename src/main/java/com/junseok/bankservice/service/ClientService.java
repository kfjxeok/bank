package com.junseok.bankservice.service;

import com.junseok.bankservice.dto.MypageDTO;
import com.junseok.bankservice.entity.Client;
import com.junseok.bankservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public MypageDTO getUserInfo(int clientId){
        Client client = clientRepository.findById(clientId);
        MypageDTO mypageDTO = new MypageDTO();
        mypageDTO.setName(client.getClientName());
        mypageDTO.setEmail(client.getClientEmail());
        mypageDTO.setPhone_num(client.getPhoneNum());
        mypageDTO.setLoginId(client.getLoginId());
        mypageDTO.setpoint(client.getPoint());
        return mypageDTO;
    }
}
