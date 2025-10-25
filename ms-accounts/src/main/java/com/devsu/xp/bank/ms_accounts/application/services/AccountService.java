package com.devsu.xp.bank.ms_accounts.application.services;

import com.devsu.xp.bank.ms_accounts.domain.models.Account;
import java.util.List;

public interface AccountService {
  Account create(Account account);
  Account getById(String id);
  List<Account> list();
  Account patchStatus(String id, Boolean status);
}
