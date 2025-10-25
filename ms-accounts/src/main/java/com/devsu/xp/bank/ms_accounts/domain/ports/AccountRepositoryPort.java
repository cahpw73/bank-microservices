package com.devsu.xp.bank.ms_accounts.domain.ports;

import com.devsu.xp.bank.ms_accounts.domain.models.Account;
import java.util.List;
import java.util.Optional;

public interface AccountRepositoryPort {
  Account save(Account account);
  Optional<Account> findById(String id);
  Optional<Account> findByNumber(String number);
  List<Account> findAll();
  boolean deleteById(String id);
}
