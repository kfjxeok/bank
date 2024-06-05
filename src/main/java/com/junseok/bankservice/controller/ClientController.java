package com.junseok.bankservice.controller;

import com.junseok.bankservice.dto.MypageDTO;
import com.junseok.bankservice.service.ClientService;
import com.junseok.bankservice.service.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private TokenProvider tokenProvider;
    @GetMapping("/me")
    public ResponseEntity<MypageDTO> getUserInfo(@RequestHeader("Authorization") String accessToken){
        //컨트롤러에서 토큰기반 이메일 추출하는 로직
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String token = accessToken.substring(7);
        boolean isValid = tokenProvider.validateToken(token);
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String tokenLoginId = tokenProvider.getUsernameFromToken(token); //
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(clientService.getUserInfo(tokenLoginId));
    }
    
}

