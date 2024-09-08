package com.junseok.bankservice.repository;

import com.junseok.bankservice.entity.ClientGiftcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientGiftcardRepository extends JpaRepository<ClientGiftcard, Integer> {

}
