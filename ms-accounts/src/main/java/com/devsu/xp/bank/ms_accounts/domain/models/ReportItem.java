package com.devsu.xp.bank.ms_accounts.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReportItem {
  private String date;
  private String customerName;
  private String accountNumber;
  private String accountType;
  private Double initialBalance;
  private Boolean status;
  private Double movement;
  private Double availableBalance;
}
