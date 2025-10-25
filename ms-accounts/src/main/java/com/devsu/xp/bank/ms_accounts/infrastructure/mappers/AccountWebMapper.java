package com.devsu.xp.bank.ms_accounts.infrastructure.mappers;

import com.devsu.xp.bank.ms_accounts.domain.models.*;
import com.devsu.xp.bank.ms_accounts.web.dtos.requests.*;
import com.devsu.xp.bank.ms_accounts.web.dtos.responses.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountWebMapper {

  // ---------- Accounts ----------
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "type", expression = "java(toAccountType(req.getType()))")
  @Mapping(target = "availableBalance", ignore = true) // se setea en service
  Account toDomain(AccountRequest req);

  @Mapping(target = "type", expression = "java(fromAccountType(src.getType()))")
  AccountResponse toResponse(Account src);

  // ---------- Helpers ----------
  default AccountType toAccountType(String v) {
    return v == null ? null : AccountType.valueOf(v.trim().toUpperCase());
  }

  default String fromAccountType(AccountType t) {
    return t == null ? null : t.name();
  }
}
