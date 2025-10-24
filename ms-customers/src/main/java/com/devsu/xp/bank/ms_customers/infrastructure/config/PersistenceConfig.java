package com.devsu.xp.bank.ms_customers.infrastructure.config;

import com.devsu.xp.bank.ms_customers.application.services.CustomerService;
import com.devsu.xp.bank.ms_customers.application.services.CustomerServiceImpl;
import com.devsu.xp.bank.ms_customers.domain.ports.CustomerRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {
  
  @Bean
  public CustomerService customerService(CustomerRepositoryPort repo) {
    // CustomerRepositoryPort ya es implementado por CustomerRepositoryAdapter (Spring lo inyecta)
    return new CustomerServiceImpl(repo);
  }
}
