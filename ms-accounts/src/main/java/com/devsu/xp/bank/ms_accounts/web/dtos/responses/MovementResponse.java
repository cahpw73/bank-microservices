package com.devsu.xp.bank.ms_accounts.web.dtos.responses;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementResponse {
  private String id;
  private String accountId;
  private String dateTime;          // ISO-8601
  private String type;              // "DEPOSIT" | "WITHDRAWAL"
  private Double amount;
  private Double resultingBalance;
}
