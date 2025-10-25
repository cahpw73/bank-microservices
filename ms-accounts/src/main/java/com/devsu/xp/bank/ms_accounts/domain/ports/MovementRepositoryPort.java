package com.devsu.xp.bank.ms_accounts.domain.ports;

import com.devsu.xp.bank.ms_accounts.domain.models.Movement;
import java.time.OffsetDateTime;
import java.util.List;

public interface MovementRepositoryPort {
  Movement save(Movement movement);
  List<Movement> findByAccountId(String accountId);
  List<Movement> findByAccountIdAndDateRange(String accountId, OffsetDateTime from, OffsetDateTime to);
}
