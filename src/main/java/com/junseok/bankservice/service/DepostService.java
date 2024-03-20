package com.junseok.bankservice.service;

import com.junseok.bankservice.dto.DepositRequestDto;
import com.junseok.bankservice.entity.Client;
import com.junseok.bankservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepostService {
    @Autowired
    private ClientRepository clientRepository;

    public void Deposit(DepositRequestDto depositRequestDto){
        Client client=clientRepository.findById(depositRequestDto.getId());
        clientRepository.save(client);
    }

}
