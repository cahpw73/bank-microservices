package com.devsu.xp.bank.ms_customers.web.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequest {
  @NotBlank
  private String name;

  @NotBlank
  private String gender; // M, F, O

  @NotNull
  @Min(0)
  @Max(120)
  private Integer age;

  @NotBlank
  private String identification;

  @NotBlank
  private String address;

  @NotBlank
  private String phone;
}
