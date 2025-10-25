package com.devsu.xp.bank.ms_accounts.infrastructure.config;

import com.devsu.xp.bank.ms_accounts.application.services.ReportService;
import com.devsu.xp.bank.ms_accounts.application.services.ReportServiceImpl;
import com.devsu.xp.bank.ms_accounts.domain.ports.AccountRepositoryPort;
import com.devsu.xp.bank.ms_accounts.domain.ports.MovementRepositoryPort;
import com.devsu.xp.bank.ms_accounts.infrastructure.client.CustomerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public ReportService reportService(
    AccountRepositoryPort accounts,
    MovementRepositoryPort movements,
    CustomerClient customerClient
  ) {
    return new ReportServiceImpl(accounts, movements, customerClient);
  }
}
