package com.devsu.xp.bank.ms_accounts.web.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementRequest {
  @NotBlank
  private String accountId;

  @NotBlank   // "DEPOSIT" | "WITHDRAWAL"
  private String type;

  @NotNull
  @Positive
  private Double amount;
}
