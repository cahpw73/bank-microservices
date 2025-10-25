package com.devsu.xp.bank.ms_accounts.infrastructure.mappers;

import com.devsu.xp.bank.ms_accounts.domain.models.Account;
import com.devsu.xp.bank.ms_accounts.infrastructure.persistence.entities.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  AccountEntity toEntity(Account src);
  Account toDomain(AccountEntity src);
}
