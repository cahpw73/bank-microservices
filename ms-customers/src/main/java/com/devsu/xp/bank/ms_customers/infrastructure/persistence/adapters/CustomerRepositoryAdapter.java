package com.devsu.xp.bank.ms_customers.infrastructure.persistence.adapters;

import com.devsu.xp.bank.ms_customers.domain.models.Customer;
import com.devsu.xp.bank.ms_customers.domain.ports.CustomerRepositoryPort;
import com.devsu.xp.bank.ms_customers.infrastructure.mappers.CustomerMapper;
import com.devsu.xp.bank.ms_customers.infrastructure.persistence.entities.CustomerEntity;
import com.devsu.xp.bank.ms_customers.infrastructure.persistence.repositories.CustomerJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {
  private final CustomerJpaRepository repository;
  private final CustomerMapper mapper;

  @Override
  public Customer save(Customer customer) {
    CustomerEntity entity = mapper.toEntity(customer);
    CustomerEntity saved = repository.save(entity);
    return mapper.toDomain(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Customer> findById(String id) {
    return repository.findById(id).map(mapper::toDomain);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Customer> findByIdentification(String identification) {
    return repository.findByIdentification(identification).map(mapper::toDomain);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Customer> findAll() {
    return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
  }

  @Override
  public boolean deleteById(String id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }
}
