package com.devsu.xp.bank.ms_customers.domain.ports;

import com.devsu.xp.bank.ms_customers.domain.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {
  Customer save(Customer customer);

  Optional<Customer> findById(String id);

  Optional<Customer> findByIdentification(String identification);

  List<Customer> findAll();

  boolean deleteById(String id);
}
