package com.devsu.xp.bank.ms_accounts.domain.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
  private String id;
  private String number;               // unique
  private AccountType type;
  private Double openingBalance;
  private Double availableBalance;
  private Boolean status;
  private String customerId;           // owner (Customer.id)
}
