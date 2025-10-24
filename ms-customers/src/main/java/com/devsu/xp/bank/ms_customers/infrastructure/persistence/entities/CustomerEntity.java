package com.devsu.xp.bank.ms_customers.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerEntity extends PersonEntity {
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private Boolean status;
}
