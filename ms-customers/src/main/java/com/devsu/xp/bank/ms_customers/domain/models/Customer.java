package com.devsu.xp.bank.ms_customers.domain.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Customer extends Person {
  private String password;
  private Boolean status;
}
