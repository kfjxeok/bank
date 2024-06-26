package com.junseok.bankservice.controller;

import com.junseok.bankservice.dto.LoginDto;
import com.junseok.bankservice.dto.SignupDTO;
import com.junseok.bankservice.dto.TokenDTO;
import com.junseok.bankservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/signup")
    public String Signup(@RequestBody SignupDTO signupDTO) {
        String Message = loginService.Signup(signupDTO);
        return Message;
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginDto loginDto){
        TokenDTO response= loginService.login(loginDto);
        return response;
    }

}
