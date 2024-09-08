package com.junseok.bankservice.repository;

import com.junseok.bankservice.entity.Client;
import com.junseok.bankservice.entity.ClientGiftcard;
import com.junseok.bankservice.entity.GiftcardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientGiftcardRepository extends JpaRepository<ClientGiftcard, Integer> {

    Optional<ClientGiftcard> findByClientAndGiftcard(Client client, GiftcardEntity giftcard);
}
