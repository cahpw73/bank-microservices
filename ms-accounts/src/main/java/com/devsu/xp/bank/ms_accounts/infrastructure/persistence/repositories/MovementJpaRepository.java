package com.devsu.xp.bank.ms_accounts.infrastructure.persistence.repositories;

import com.devsu.xp.bank.ms_accounts.infrastructure.persistence.entities.MovementEntity;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementJpaRepository extends JpaRepository<MovementEntity, String> {
  List<MovementEntity> findByAccountIdOrderByDateTimeAsc(String accountId);
  List<MovementEntity> findByAccountIdAndDateTimeBetweenOrderByDateTimeAsc(
    String accountId,
    OffsetDateTime from,
    OffsetDateTime to
  );
}
