package com.junseok.bankservice.repository;

import com.junseok.bankservice.entity.GiftcardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftcardRepository extends JpaRepository<GiftcardEntity, Integer> {
    GiftcardEntity findByName (String name);

}
