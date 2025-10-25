package com.devsu.xp.bank.ms_accounts.infrastructure.persistence.entities;

import com.devsu.xp.bank.ms_accounts.domain.models.MovementType;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.*;

@Entity
@Table(
  name = "movements",
  indexes = {
    @Index(name = "ix_movements_account", columnList = "account_id"),
    @Index(name = "ix_movements_datetime", columnList = "date_time")
  }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementEntity {
  @Id
  @Column(length = 36)
  private String id;

  @Column(name = "account_id", nullable = false, length = 36)
  private String accountId;

  @Column(name = "date_time", nullable = false)
  private OffsetDateTime dateTime;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 16)
  private MovementType type;

  @Column(nullable = false)
  private Double amount;

  @Column(name = "resulting_balance", nullable = false)
  private Double resultingBalance;
}
