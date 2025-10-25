package com.devsu.xp.bank.ms_accounts.web.dtos.requests;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountPatchRequest {
  private Boolean status;
}
