package com.junseok.bankservice.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.junseok.bankservice.entity.Client;
import com.junseok.bankservice.entity.ClientGiftcard;
import com.junseok.bankservice.entity.GiftcardEntity;
import com.junseok.bankservice.repository.ClientGiftcardRepository;
import com.junseok.bankservice.repository.ClientRepository;
import com.junseok.bankservice.repository.GiftcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Service
public class QRCodeService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private GiftcardRepository giftcardRepository;
    @Autowired
    private ClientGiftcardRepository clientGiftcardRepository;

    public BufferedImage generateQRCode(String token,String giftCardName, int width, int height) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        // 기프트카드 사용 완료 처리를 위한 URL 생성
        String url = "http://localhost:3000/giftcard/usegiftcard.html?token=" + token+"&giftCardName="+giftCardName;

        // QR 코드 생성
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public ResponseEntity<String> useGiftCard(String email, String giftCardName){
        // 사용자를 데이터베이스에서 찾음
        MapReactiveUserDetailsService clientService;
        Client client = clientRepository.findByClientEmail(email).orElseThrow();
        if (client == null) {
            return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.1");
        }

        // 기프트카드 정보 조회
        GiftcardEntity giftcard = giftcardRepository.findByName(giftCardName);
        if (giftcard == null) {
            return ResponseEntity.status(404).body("해당 기프트카드를 찾을 수 없습니다.2");
        }

        // 클라이언트가 소유한 기프트카드 조회
        ClientGiftcard clientGiftcard = clientGiftcardRepository.findByClientAndGiftcard(client,giftcard).orElseThrow();
        if (clientGiftcard == null || clientGiftcard.getQuantity() <= 0) {
            return ResponseEntity.status(400).body("기프트카드를 사용할 수 없습니다. 수량이 부족합니다.3");
        }
        clientGiftcard.setQuantity(clientGiftcard.getQuantity() - 1);

        double pointToDeduct = giftcard.getPoint();
        if (client.getPoint() < pointToDeduct) {
            return ResponseEntity.status(400).body("포인트가 부족합니다.4");
        }
        client.setPoint(client.getPoint() - pointToDeduct);

        clientRepository.save(client);
        clientGiftcardRepository.save(clientGiftcard);
        return ResponseEntity.ok("Success");
    }
}
