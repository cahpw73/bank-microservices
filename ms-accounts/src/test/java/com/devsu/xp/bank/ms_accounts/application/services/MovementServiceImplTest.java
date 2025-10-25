package com.devsu.xp.bank.ms_accounts.application.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.devsu.xp.bank.ms_accounts.domain.models.*;
import com.devsu.xp.bank.ms_accounts.domain.ports.AccountRepositoryPort;
import com.devsu.xp.bank.ms_accounts.domain.ports.MovementRepositoryPort;
import com.devsu.xp.bank.shared_kernel.error.InsufficientBalanceException;
import com.devsu.xp.bank.shared_kernel.error.NotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class MovementServiceImplTest {

  private AccountRepositoryPort accounts;
  private MovementRepositoryPort movements;
  private MovementService service;

  @BeforeEach
  void setUp() {
    accounts = mock(AccountRepositoryPort.class);
    movements = mock(MovementRepositoryPort.class);
    service = new MovementServiceImpl(accounts, movements);
  }

  @Test
  void deposit_shouldIncreaseBalance_andPersistMovement() {
    Account acc = Account.builder()
      .id("acc-1")
      .number("123")
      .type(AccountType.SAVINGS)
      .openingBalance(1000.0)
      .availableBalance(1000.0)
      .status(true)
      .customerId("cust-1")
      .build();

    when(accounts.findById("acc-1")).thenReturn(Optional.of(acc));
    when(accounts.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));
    when(movements.save(any(Movement.class))).thenAnswer(inv -> inv.getArgument(0));

    Movement req = Movement.builder()
      .accountId("acc-1")
      .type(MovementType.DEPOSIT)
      .amount(200.0)
      .build();

    Movement saved = service.register(req);

    assertNotNull(saved.getId());
    assertNotNull(saved.getDateTime());
    assertEquals(1200.0, saved.getResultingBalance());

    // Verificar que la cuenta se actualizó
    ArgumentCaptor<Account> accCaptor = ArgumentCaptor.forClass(Account.class);
    verify(accounts).save(accCaptor.capture());
    assertEquals(1200.0, accCaptor.getValue().getAvailableBalance());

    // Movimiento persistido
    verify(movements).save(any(Movement.class));
  }

  @Test
  void withdrawal_shouldDecreaseBalance() {
    Account acc = Account.builder()
      .id("acc-1")
      .number("123")
      .type(AccountType.SAVINGS)
      .openingBalance(500.0)
      .availableBalance(500.0)
      .status(true)
      .customerId("cust-1")
      .build();

    when(accounts.findById("acc-1")).thenReturn(Optional.of(acc));
    when(accounts.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));
    when(movements.save(any(Movement.class))).thenAnswer(inv -> inv.getArgument(0));

    Movement req = Movement.builder()
      .accountId("acc-1")
      .type(MovementType.WITHDRAWAL)
      .amount(200.0)
      .build();

    Movement saved = service.register(req);

    assertEquals(300.0, saved.getResultingBalance());
    verify(accounts).save(any(Account.class));
    verify(movements).save(any(Movement.class));
  }

  @Test
  void withdrawal_withInsufficientBalance_shouldThrow() {
    Account acc = Account.builder()
      .id("acc-1")
      .number("123")
      .type(AccountType.SAVINGS)
      .openingBalance(100.0)
      .availableBalance(100.0)
      .status(true)
      .customerId("cust-1")
      .build();

    when(accounts.findById("acc-1")).thenReturn(Optional.of(acc));

    Movement req = Movement.builder()
      .accountId("acc-1")
      .type(MovementType.WITHDRAWAL)
      .amount(200.0)
      .build();

    assertThrows(InsufficientBalanceException.class, () -> service.register(req));
    verify(movements, never()).save(any());
  }

  @Test
  void register_withUnknownAccount_shouldThrow() {
    when(accounts.findById("acc-x")).thenReturn(Optional.empty());

    Movement req = Movement.builder()
      .accountId("acc-x")
      .type(MovementType.DEPOSIT)
      .amount(50.0)
      .build();

    assertThrows(NotFoundException.class, () -> service.register(req));
    verify(movements, never()).save(any());
  }

  @Test
  void listByAccount_shouldDelegateToRepository() {
    service.listByAccount("acc-1");
    verify(movements).findByAccountId("acc-1");
  }
}
