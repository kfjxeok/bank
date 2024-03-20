package com.junseok.bankservice.service;


import com.junseok.bankservice.dto.SignupDTO;
import com.junseok.bankservice.entity.Client;
import com.junseok.bankservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private ClientRepository clientRepository;

    public void Signup(SignupDTO signupDTO){
        String clientName=signupDTO.getClientName();
        String clientEmail=signupDTO.getClientEmail();
        String passWord=signupDTO.getPassWord();
        String accountNum=signupDTO.getAccountNum();
        int phoneNum=signupDTO.getPhoneNum();
        Client client=new Client(clientName,clientEmail,passWord,phoneNum);
        clientRepository.save(client);


    }

}
