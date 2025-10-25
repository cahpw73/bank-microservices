package com.devsu.xp.bank.ms_accounts.application.services;

import com.devsu.xp.bank.ms_accounts.domain.models.Account;
import com.devsu.xp.bank.ms_accounts.domain.ports.AccountRepositoryPort;
import com.devsu.xp.bank.shared_kernel.error.BusinessException;
import com.devsu.xp.bank.shared_kernel.error.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepositoryPort accounts;

  @Override
  public Account create(Account account) {
    // unique account number
    Optional<Account> existing = accounts.findByNumber(account.getNumber());
    if (existing.isPresent()) {
      throw new BusinessException("ACCOUNT_DUPLICATE", "Account number already exists");
    }

    // id + initial available balance
    if (account.getId() == null || account.getId().isBlank()) {
      account.setId(UUID.randomUUID().toString());
    }
    if (account.getOpeningBalance() == null) {
      account.setOpeningBalance(0.0);
    }
    if (account.getAvailableBalance() == null) {
      account.setAvailableBalance(account.getOpeningBalance());
    }
    if (account.getStatus() == null) {
      account.setStatus(Boolean.TRUE);
    }
    return accounts.save(account);
  }

  @Override
  public Account getById(String id) {
    return accounts
      .findById(id)
      .orElseThrow(() -> new NotFoundException("ACCOUNT_NOT_FOUND", "Account not found"));
  }

  @Override
  public List<Account> list() {
    return accounts.findAll();
  }

  @Override
  public Account patchStatus(String id, Boolean status) {
    Account current = getById(id);
    if (status != null) {
      current.setStatus(status);
    }
    return accounts.save(current);
  }
}
