package com.junseok.bankservice.controller;

import com.junseok.bankservice.service.GiftcardService;
import com.junseok.bankservice.service.QRCodeService;
import com.junseok.bankservice.service.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private GiftcardService giftcardService;

    @GetMapping("/generateQRCode")
    public ResponseEntity<Map<String, String>> generateQRCode(@RequestHeader("Authorization") String accessToken, @RequestParam String giftCardName) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            if (!giftcardService.doesClientOwnGiftcard(username, giftCardName)) {
                return ResponseEntity.status(403).body(null);
            }

            String token = tokenProvider.generateTokenWithGiftCard(username, giftCardName);

            BufferedImage qrImage = qrCodeService.generateQRCode(token, 200, 200);

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
}
