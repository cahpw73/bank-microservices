package com.devsu.xp.bank.ms_customers.web.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {
  @NotBlank
  private String password;

  @NotNull
  private Boolean status;

  @NotNull
  private PersonRequest person; // inherited data
}
