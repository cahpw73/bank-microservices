package com.devsu.xp.bank.ms_customers.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.xp.bank.ms_customers.infrastructure.persistence.entities.CustomerEntity;
import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, String> {
  Optional<CustomerEntity> findByIdentification(String identification);
}
