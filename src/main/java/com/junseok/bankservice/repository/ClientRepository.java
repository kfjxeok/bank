package com.junseok.bankservice.repository;

import com.junseok.bankservice.entity.Client;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findById(int id);
    Optional<Client> findByClientEmail(String clientEmail);


    boolean existsByClientEmail(String clientEmail);
}
