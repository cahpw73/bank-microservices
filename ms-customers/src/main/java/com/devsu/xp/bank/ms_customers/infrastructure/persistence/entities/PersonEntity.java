package com.devsu.xp.bank.ms_customers.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PersonEntity {
  @Id
  @Column(length = 36)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, length = 1)
  private String gender; // M/F/O

  @Column(nullable = false)
  private Integer age;

  @Column(nullable = false, unique = true)
  private String identification;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private String phone;
}
