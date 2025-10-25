package com.devsu.xp.bank.ms_accounts.infrastructure.persistence.adapters;

import com.devsu.xp.bank.ms_accounts.domain.models.Movement;
import com.devsu.xp.bank.ms_accounts.domain.ports.MovementRepositoryPort;
import com.devsu.xp.bank.ms_accounts.infrastructure.mappers.MovementMapper;
import com.devsu.xp.bank.ms_accounts.infrastructure.persistence.repositories.MovementJpaRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class MovementRepositoryAdapter implements MovementRepositoryPort {

  private final MovementJpaRepository repository;
  private final MovementMapper mapper;

  @Override
  public Movement save(Movement movement) {
    return mapper.toDomain(repository.save(mapper.toEntity(movement)));
  }

  @Override
  @Transactional(readOnly = true)
  public List<Movement> findByAccountId(String accountId) {
    return repository.findByAccountIdOrderByDateTimeAsc(accountId)
      .stream().map(mapper::toDomain).collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public List<Movement> findByAccountIdAndDateRange(String accountId, OffsetDateTime from, OffsetDateTime to) {
    return repository.findByAccountIdAndDateTimeBetweenOrderByDateTimeAsc(accountId, from, to)
      .stream().map(mapper::toDomain).collect(java.util.stream.Collectors.toList());
  }
}
