package com.devsu.xp.bank.ms_accounts.domain.models;

import java.time.OffsetDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movement {
  private String id;
  private String accountId;
  private OffsetDateTime dateTime;     // event time
  private MovementType type;           // DEPOSIT | WITHDRAWAL
  private Double amount;               // positive number
  private Double resultingBalance;     // balance after applying movement
}
