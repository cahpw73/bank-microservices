package com.devsu.xp.bank.ms_accounts.infrastructure.config;

import com.devsu.xp.bank.ms_accounts.application.services.*;
import com.devsu.xp.bank.ms_accounts.domain.ports.AccountRepositoryPort;
import com.devsu.xp.bank.ms_accounts.domain.ports.MovementRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

  @Bean
  public AccountService accountService(AccountRepositoryPort accounts) {
    return new AccountServiceImpl(accounts);
  }

  @Bean
  public MovementService movementService(AccountRepositoryPort accounts, MovementRepositoryPort movements) {
    return new MovementServiceImpl(accounts, movements);
  }
}
