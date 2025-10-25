package com.devsu.xp.bank.ms_accounts.web.dtos.responses;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {
  private String id;
  private String number;
  private String type;            // "SAVINGS" | "CHECKING"
  private Double openingBalance;
  private Double availableBalance;
  private Boolean status;
  private String customerId;
}
