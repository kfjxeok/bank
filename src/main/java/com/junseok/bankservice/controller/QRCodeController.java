package com.junseok.bankservice.controller;

import com.junseok.bankservice.service.GiftcardService;
import com.junseok.bankservice.service.QRCodeService;
import com.junseok.bankservice.service.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/generateQRCode")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private GiftcardService giftcardService;

    @GetMapping
    public ResponseEntity<Map<String, String>> generateQRCode(@RequestHeader("Authorization") String accessToken, @RequestParam String giftCardName) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            if (!giftcardService.doesClientOwnGiftcard(username, giftCardName)) {
                return ResponseEntity.status(403).body(null);
            }

            String token = tokenProvider.generateTokenWithGiftCard(username, giftCardName);

            BufferedImage qrImage = qrCodeService.generateQRCode(token,giftCardName, 200, 200);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "png", baos);
            byte[] imageData = baos.toByteArray();

            // Base64로 인코딩
            String base64Image = Base64.getEncoder().encodeToString(imageData);

            Map<String, String> response = new HashMap<>();
            response.put("image", base64Image);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            return ResponseEntity.ok().headers(headers).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    @PostMapping("/useGiftCard")
    public ResponseEntity<String> useGiftCard(@RequestParam String token) {
        try {
            String username = tokenProvider.getUsernameFromToken(token);
            System.out.println(username);
            String giftCardName = tokenProvider.getGiftCardNameFromToken(token);
            System.out.println(giftCardName);

            ResponseEntity<String> response = qrCodeService.useGiftCard(username,giftCardName);

            return response;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("기프트카드 사용 중 오류가 발생했습니다.5");
        }
    }
}
