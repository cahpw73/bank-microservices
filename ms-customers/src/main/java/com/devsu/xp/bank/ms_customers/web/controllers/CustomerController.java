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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
  
  private final CustomerService service;
  private final CustomerWebMapper mapper;

  @GetMapping
  public List<CustomerResponse> list() {
    log.info("GET /customers - Listing customers");
    List<CustomerResponse> list = service.list().stream().map(mapper::toResponse).collect(Collectors.toList());
    log.info("Customers listed: count={}", list.size());
    return list;
  }

  @GetMapping("/{id}")
  public CustomerResponse get(@PathVariable String id) {
    log.info("GET /customers/{} - Fetching customer", id);
    CustomerResponse response = mapper.toResponse(service.getById(id));
    log.info("Customer fetched: id={}, name={}", response.getId(), response.getPerson().getName());
    return response;
  }

  @PostMapping
  public ResponseEntity<CustomerResponse> create(@RequestBody @Valid CustomerRequest request) {
    log.info("POST /customers - Creating customer: name={}, identification={}",
        request.getPerson().getName(), request.getPerson().getIdentification());
    Customer created = service.create(mapper.toDomain(request));
    log.info("Customer created: id={}, name={}", created.getId(), created.getName());
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
  }

  @PutMapping("/{id}")
  public CustomerResponse update(@PathVariable String id, @RequestBody @Valid CustomerRequest request) {
    log.info("PUT /customers/{} - Updating customer", id);
    Customer updated = service.update(id, mapper.toDomain(request));
    log.info("Customer updated: id={}, name={}", updated.getId(), updated.getName());
    return mapper.toResponse(updated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    log.info("DELETE /customers/{} - Deleting customer", id);
    service.delete(id);
    log.info("Customer deleted: id={}", id);
    return ResponseEntity.noContent().build();
  }
}
