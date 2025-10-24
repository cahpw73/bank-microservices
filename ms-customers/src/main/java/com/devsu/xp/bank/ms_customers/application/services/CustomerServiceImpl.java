package com.devsu.xp.bank.ms_customers.application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.devsu.xp.bank.ms_customers.domain.models.Customer;
import com.devsu.xp.bank.ms_customers.domain.ports.CustomerRepositoryPort;
import com.devsu.xp.bank.shared_kernel.error.BusinessException;
import com.devsu.xp.bank.shared_kernel.error.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepositoryPort repository;

  @Override
  public Customer create(Customer customer) {
    // identification must be unique
    Optional<Customer> existing = repository.findByIdentification(customer.getIdentification());
    if (existing.isPresent()) {
      throw new BusinessException("CUSTOMER_DUPLICATE", "Identification already exists");
    }
    // id (shared for Person/Customer hierarchy)
    if (customer.getId() == null || customer.getId().isBlank()) {
      customer.setId(UUID.randomUUID().toString());
    }
    return repository.save(customer);
  }

  @Override
  public Customer getById(String id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new NotFoundException("CUSTOMER_NOT_FOUND", "Customer not found"));
  }

  @Override
  public List<Customer> list() {
    return repository.findAll();
  }

  @Override
  public Customer update(String id, Customer customer) {
    Customer current = getById(id);

    // If identification changes, enforce uniqueness
    if (
      customer.getIdentification() != null &&
      !customer.getIdentification().equalsIgnoreCase(current.getIdentification())
    ) {
      repository
        .findByIdentification(customer.getIdentification())
        .ifPresent(x -> { throw new BusinessException("CUSTOMER_DUPLICATE", "Identification already exists"); });
    }

    // Preserve id; rest of fields taken from incoming object if not null (simple overwrite for now)
    customer.setId(id);
    return repository.save(customer);
  }

  @Override
  public void delete(String id) {
    boolean removed = repository.deleteById(id);
    if (!removed) {
      throw new NotFoundException("CUSTOMER_NOT_FOUND", "Customer not found");
    }
  }
}
