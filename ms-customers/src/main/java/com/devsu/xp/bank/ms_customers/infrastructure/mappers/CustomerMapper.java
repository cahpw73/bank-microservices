package com.devsu.xp.bank.ms_customers.infrastructure.mappers;

import com.devsu.xp.bank.ms_customers.domain.models.Customer;
import com.devsu.xp.bank.ms_customers.infrastructure.persistence.entities.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
  CustomerEntity toEntity(Customer src);
  Customer toDomain(CustomerEntity src);
}
