package com.devsu.xp.bank.ms_customers.web.dtos.responses;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
  private String id;
  private Boolean status;
  private PersonResponse person;
}
