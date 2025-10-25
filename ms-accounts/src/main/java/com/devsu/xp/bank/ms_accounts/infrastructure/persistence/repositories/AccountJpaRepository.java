package com.devsu.xp.bank.ms_accounts.infrastructure.persistence.repositories;

import com.devsu.xp.bank.ms_accounts.infrastructure.persistence.entities.AccountEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, String> {
  Optional<AccountEntity> findByNumber(String number);
}
