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
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService service;
  private final AccountWebMapper mapper;

  @PostMapping
  public ResponseEntity<AccountResponse> create(@RequestBody @Valid AccountRequest req) {
    Account created = service.create(mapper.toDomain(req));
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
  }

  @GetMapping("/{id}")
  public AccountResponse get(@PathVariable("id") String id) {
    return mapper.toResponse(service.getById(id));
  }

  @GetMapping
  public List<AccountResponse> list() {
    return service.list().stream().map(mapper::toResponse).collect(Collectors.toList());
  }

  @PatchMapping("/{id}")
  public AccountResponse patch(@PathVariable("id") String id, @RequestBody AccountPatchRequest patch) {
    Account updated = service.patchStatus(id, patch.getStatus());
    return mapper.toResponse(updated);
  }
}
