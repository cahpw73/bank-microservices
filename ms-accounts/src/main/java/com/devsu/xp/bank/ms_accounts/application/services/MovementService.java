package com.devsu.xp.bank.ms_accounts.application.services;

import com.devsu.xp.bank.ms_accounts.domain.models.Movement;
import java.util.List;

public interface MovementService {
  Movement register(Movement movement);
  List<Movement> listByAccount(String accountId);
}
