package com.devsu.xp.bank.ms_customers.domain.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Person {
  private String id;
  private String name;
  private String gender;           // M, F, O (Other)
  private Integer age;
  private String identification;   // Unique
  private String address;
  private String phone;
}
