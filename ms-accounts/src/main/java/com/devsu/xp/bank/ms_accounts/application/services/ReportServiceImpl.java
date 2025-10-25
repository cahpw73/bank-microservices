package com.devsu.xp.bank.ms_accounts.application.services;

import com.devsu.xp.bank.ms_accounts.domain.models.Account;
import com.devsu.xp.bank.ms_accounts.domain.models.Movement;
import com.devsu.xp.bank.ms_accounts.domain.models.ReportItem;
import com.devsu.xp.bank.ms_accounts.domain.ports.AccountRepositoryPort;
import com.devsu.xp.bank.ms_accounts.domain.ports.MovementRepositoryPort;
import com.devsu.xp.bank.ms_accounts.infrastructure.client.CustomerClient;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

  private final AccountRepositoryPort accounts;
  private final MovementRepositoryPort movements;
  private final CustomerClient customerClient;

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  @Override
  public List<ReportItem> getReport(String customerId, LocalDate from, LocalDate to) {
    String customerName = customerClient.getCustomerName(customerId);

    List<Account> customerAccounts = accounts.findAll().stream()
      .filter(acc -> acc.getCustomerId().equals(customerId))
      .toList();

    List<ReportItem> report = new ArrayList<>();

    for (Account acc : customerAccounts) {
      OffsetDateTime fromDateTime = from.atStartOfDay().atOffset(OffsetDateTime.now().getOffset());
      OffsetDateTime toDateTime = to.plusDays(1).atStartOfDay().atOffset(OffsetDateTime.now().getOffset());

      List<Movement> movementList = movements.findByAccountIdAndDateRange(acc.getId(), fromDateTime, toDateTime);

      for (Movement m : movementList) {
        report.add(
          ReportItem.builder()
            .date(m.getDateTime().format(DATE_FORMAT))
            .customerName(customerName)
            .accountNumber(acc.getNumber())
            .accountType(acc.getType().name())
            .initialBalance(acc.getOpeningBalance())
            .status(acc.getStatus())
            .movement(m.getType().name().equals("WITHDRAWAL") ? -m.getAmount() : m.getAmount())
            .availableBalance(m.getResultingBalance())
            .build()
        );
      }
    }
    return report;
  }
}
