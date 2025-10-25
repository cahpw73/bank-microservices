package com.devsu.xp.bank.ms_accounts.web.controllers;

import com.devsu.xp.bank.ms_accounts.application.services.AccountService;
import com.devsu.xp.bank.ms_accounts.domain.models.Account;
import com.devsu.xp.bank.ms_accounts.infrastructure.mappers.AccountWebMapper;
import com.devsu.xp.bank.ms_accounts.web.dtos.requests.AccountPatchRequest;
import com.devsu.xp.bank.ms_accounts.web.dtos.requests.AccountRequest;
import com.devsu.xp.bank.ms_accounts.web.dtos.responses.AccountResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService service;
  private final AccountWebMapper mapper;

  @PostMapping
  public ResponseEntity<AccountResponse> create(@RequestBody @Valid AccountRequest req) {
    log.info("POST /accounts - Creating account for customerId={}", req.getCustomerId());
    Account created = service.create(mapper.toDomain(req));
    log.info("Account created: id={}, number={}", created.getId(), created.getNumber());
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
  }

  @GetMapping("/{id}")
  public AccountResponse get(@PathVariable("id") String id) {
    log.info("GET /accounts/{} - Fetching account", id);
    AccountResponse response = mapper.toResponse(service.getById(id));
    log.info("Account retrieved: id={}, number={}", response.getId(), response.getNumber());
    return response;
  }

  @GetMapping
  public List<AccountResponse> list() {
    log.info("GET /accounts - Listing accounts");
    List<AccountResponse> list = service.list().stream().map(mapper::toResponse).collect(Collectors.toList());
    log.info("Accounts listed: count={}", list.size());
    return list;
  }

  @PatchMapping("/{id}")
  public AccountResponse patch(@PathVariable("id") String id, @RequestBody AccountPatchRequest patch) {
    log.info("PATCH /accounts/{} - Updating status to {}", id, patch.getStatus());
    Account updated = service.patchStatus(id, patch.getStatus());
    log.info("Account updated: id={}, newStatus={}", updated.getId(), updated.getStatus());
    return mapper.toResponse(updated);
  }
}
