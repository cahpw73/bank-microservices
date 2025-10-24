package com.devsu.xp.bank.ms_customers.web.controllers;

import com.devsu.xp.bank.ms_customers.application.services.CustomerService;
import com.devsu.xp.bank.ms_customers.domain.models.Customer;
import com.devsu.xp.bank.ms_customers.infrastructure.mappers.CustomerWebMapper;
import com.devsu.xp.bank.ms_customers.web.dtos.requests.CustomerRequest;
import com.devsu.xp.bank.ms_customers.web.dtos.responses.CustomerResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
  
  private final CustomerService service;
  private final CustomerWebMapper mapper;

  @GetMapping
  public List<CustomerResponse> list() {
    return service.list().stream().map(mapper::toResponse).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public CustomerResponse get(@PathVariable String id) {
    return mapper.toResponse(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<CustomerResponse> create(@RequestBody @Valid CustomerRequest request) {
    Customer created = service.create(mapper.toDomain(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
  }

  @PutMapping("/{id}")
  public CustomerResponse update(@PathVariable String id, @RequestBody @Valid CustomerRequest request) {
    Customer updated = service.update(id, mapper.toDomain(request));
    return mapper.toResponse(updated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
