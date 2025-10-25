package com.devsu.xp.bank.ms_accounts.infrastructure.persistence.adapters;

import com.devsu.xp.bank.ms_accounts.domain.models.Account;
import com.devsu.xp.bank.ms_accounts.domain.ports.AccountRepositoryPort;
import com.devsu.xp.bank.ms_accounts.infrastructure.mappers.AccountMapper;
import com.devsu.xp.bank.ms_accounts.infrastructure.persistence.entities.AccountEntity;
import com.devsu.xp.bank.ms_accounts.infrastructure.persistence.repositories.AccountJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class AccountRepositoryAdapter implements AccountRepositoryPort {

  private final AccountJpaRepository repository;
  private final AccountMapper mapper;

  @Override
  public Account save(Account account) {
    AccountEntity saved = repository.save(mapper.toEntity(account));
    return mapper.toDomain(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Account> findById(String id) {
    return repository.findById(id).map(mapper::toDomain);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Account> findByNumber(String number) {
    return repository.findByNumber(number).map(mapper::toDomain);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Account> findAll() {
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
