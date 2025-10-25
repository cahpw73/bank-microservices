package com.devsu.xp.bank.ms_accounts.infrastructure.persistence.entities;

import com.devsu.xp.bank.ms_accounts.domain.models.AccountType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
  name = "accounts",
  uniqueConstraints = @UniqueConstraint(name = "uk_accounts_number", columnNames = "number"),
  indexes = {
    @Index(name = "ix_accounts_customer", columnList = "customer_id")
  }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountEntity {
  @Id
  @Column(length = 36)
  private String id;

  @Column(nullable = false, length = 32)
  private String number;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 16)
  private AccountType type;

  @Column(name = "opening_balance", nullable = false)
  private Double openingBalance;

  @Column(name = "available_balance", nullable = false)
  private Double availableBalance;

  @Column(nullable = false)
  private Boolean status;

  @Column(name = "customer_id", nullable = false, length = 36)
  private String customerId;
}
