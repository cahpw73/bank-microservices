package com.devsu.xp.bank.ms_accounts.application.services;

import com.devsu.xp.bank.ms_accounts.domain.models.Account;
import com.devsu.xp.bank.ms_accounts.domain.models.Movement;
import com.devsu.xp.bank.ms_accounts.domain.models.MovementType;
import com.devsu.xp.bank.ms_accounts.domain.ports.AccountRepositoryPort;
import com.devsu.xp.bank.ms_accounts.domain.ports.MovementRepositoryPort;
import com.devsu.xp.bank.shared_kernel.error.InsufficientBalanceException;
import com.devsu.xp.bank.shared_kernel.error.NotFoundException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

  private final AccountRepositoryPort accounts;
  private final MovementRepositoryPort movements;

  @Override
  public Movement register(Movement movement) {
    // Guard rails
    if (movement.getAmount() == null || movement.getAmount() <= 0.0) {
      throw new IllegalArgumentException("Movement amount must be positive");
    }
    if (movement.getType() == null) {
      throw new IllegalArgumentException("Movement type is required");
    }

    // Ensure account exists and is active
    Account account = accounts
      .findById(movement.getAccountId())
      .orElseThrow(() -> new NotFoundException("ACCOUNT_NOT_FOUND", "Account not found"));
    if (Boolean.FALSE.equals(account.getStatus())) {
      throw new IllegalStateException("Account is disabled");
    }

    // Apply movement
    double current = account.getAvailableBalance() != null ? account.getAvailableBalance() : 0.0;
    double resulting;

    if (movement.getType() == MovementType.DEPOSIT) {
      resulting = current + movement.getAmount();
    } else { // WITHDRAWAL
      if (movement.getAmount() > current) {
        throw new InsufficientBalanceException("Insufficient balance");
      }
      resulting = current - movement.getAmount();
    }

    // Fill movement fields
    if (movement.getId() == null || movement.getId().isBlank()) {
      movement.setId(UUID.randomUUID().toString());
    }
    if (movement.getDateTime() == null) {
      movement.setDateTime(OffsetDateTime.now());
    }
    movement.setResultingBalance(resulting);

    // Persist: first update account balance, then save movement
    account.setAvailableBalance(resulting);
    accounts.save(account);
    return movements.save(movement);
  }

  @Override
  public List<Movement> listByAccount(String accountId) {
    // you could check account existence if desired
    return movements.findByAccountId(accountId);
  }
}
