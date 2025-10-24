package com.devsu.xp.bank.ms_customers.web.dtos.responses;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonResponse {
  private String id;
  private String name;
  private String gender;
  private Integer age;
  private String identification;
  private String address;
  private String phone;
}
