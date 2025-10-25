package com.devsu.xp.bank.ms_accounts.infrastructure.mappers;

import com.devsu.xp.bank.ms_accounts.domain.models.Movement;
import com.devsu.xp.bank.ms_accounts.infrastructure.persistence.entities.MovementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementMapper {
  MovementEntity toEntity(Movement src);
  Movement toDomain(MovementEntity src);
}
