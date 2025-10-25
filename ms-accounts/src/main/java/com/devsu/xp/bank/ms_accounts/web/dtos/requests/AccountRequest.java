package com.devsu.xp.bank.ms_accounts.web.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {
  @NotBlank
  private String number;

  @NotBlank   // "SAVINGS" | "CHECKING"
  private String type;

  @NotNull
  private Double openingBalance;

  @NotNull
  private Boolean status;

  @NotBlank
  private String customerId;
}
