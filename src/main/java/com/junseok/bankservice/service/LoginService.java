package com.junseok.bankservice.service;


import com.junseok.bankservice.dto.LoginDto;
import com.junseok.bankservice.dto.SignupDTO;
import com.junseok.bankservice.dto.TokenDTO;
import com.junseok.bankservice.entity.Authority;
import com.junseok.bankservice.entity.Client;
import com.junseok.bankservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Service
public class LoginService {

    private final AuthenticationManagerBuilder managerBuilder;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    public LoginService(AuthenticationManagerBuilder managerBuilder, TokenProvider tokenProvider) {
        this.managerBuilder = managerBuilder;
        this.tokenProvider = tokenProvider;
    }

    public String Signup(SignupDTO signupDTO){
        if(clientRepository.existsByClientEmail(signupDTO.getClientEmail())){
            return "already assigned email.";
        }
        String clientName=signupDTO.getClientName();
        String clientEmail=signupDTO.getClientEmail();
        String passWord=signupDTO.getPassWord();
        passWord=passwordEncoder.encode(passWord);
        String accountNum=signupDTO.getAccountNum();
        String phoneNum=signupDTO.getPhoneNum();
        Client client=new Client(clientName,clientEmail,passWord,phoneNum);
        String customertype=signupDTO.getCustomertype();
        if (customertype.equals("customer"))
        {
            client.setAuthority(Authority.ROLE_USER);
        } else if (customertype.equals("master"))
        {
            client.setAuthority(Authority.ROLE_MASTER);
        }
        else
        {
            return "권한을 선택해 주세요.";
        }
        clientRepository.save(client);
    return "success" ;
    }

    public TokenDTO login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication(loginDto.getClientEmail(), loginDto.getPassWord());
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
        //궁극적으로 만들어야 할 SpringSecurity 출입증.
        return tokenProvider.generateTokenDto(authentication);
    }

}
