package com.devsu.xp.bank.ms_customers.infrastructure.mappers;

import com.devsu.xp.bank.ms_customers.domain.models.*;
import com.devsu.xp.bank.ms_customers.web.dtos.requests.*;
import com.devsu.xp.bank.ms_customers.web.dtos.responses.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {
  // Requests -> Domain
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", source = "person.name")
  @Mapping(target = "gender", source = "person.gender")
  @Mapping(target = "age", source = "person.age")
  @Mapping(target = "identification", source = "person.identification")
  @Mapping(target = "address", source = "person.address")
  @Mapping(target = "phone", source = "person.phone")
  Customer toDomain(CustomerRequest request);

  // Domain -> Responses
  @Mapping(target = "person.id", source = "id")
  @Mapping(target = "person.name", source = "name")
  @Mapping(target = "person.gender", source = "gender")
  @Mapping(target = "person.age", source = "age")
  @Mapping(target = "person.identification", source = "identification")
  @Mapping(target = "person.address", source = "address")
  @Mapping(target = "person.phone", source = "phone")
  CustomerResponse toResponse(Customer domain);

  // Helpers Person <-> DTO if you need them explicitly
  Person toPerson(PersonRequest request);

  PersonResponse toPersonResponse(Person person);
}
