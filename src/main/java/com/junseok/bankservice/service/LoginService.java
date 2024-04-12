package com.junseok.bankservice.service;


import com.junseok.bankservice.dto.LoginDto;
import com.junseok.bankservice.dto.SignupDTO;
import com.junseok.bankservice.entity.Client;
import com.junseok.bankservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String Signup(SignupDTO signupDTO){
        if(clientRepository.findByClientEmail(signupDTO.getClientEmail())==null){
            return "이미 가입되어 있는 이메일입니다.";
        }
        String clientName=signupDTO.getClientName();
        String clientEmail=signupDTO.getClientEmail();
        String passWord=signupDTO.getPassWord();
        passWord=passwordEncoder.encode(passWord);
        String accountNum=signupDTO.getAccountNum();
        String phoneNum=signupDTO.getPhoneNum();
        Client client=new Client(clientName,clientEmail,passWord,phoneNum);
        clientRepository.save(client);

    return "성공" ;
    }
    public String login(LoginDto loginDto){
        String clientEmail=loginDto.getClientEmail();
        String passWord= loginDto.getPassWord();

        Client client=clientRepository.findByClientEmail(clientEmail);
        if (client.getPassWord().equals(passWord)){
            return client.getClientName();
            //System.out.println("비밀번호일치");

        }
        else{
            return "fail";
        }
        //System.out.println(client);
    }

}
