package com.junseok.bankservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginDto {
    private String clientEmail;
    private String passWord;

    public UsernamePasswordAuthenticationToken toAuthentication(String email, String password) {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
