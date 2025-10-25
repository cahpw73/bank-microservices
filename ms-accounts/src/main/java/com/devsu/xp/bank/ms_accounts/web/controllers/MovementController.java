package com.devsu.xp.bank.ms_accounts.web.controllers;

import com.devsu.xp.bank.ms_accounts.application.services.MovementService;
import com.devsu.xp.bank.ms_accounts.infrastructure.mappers.MovementWebMapper;
import com.devsu.xp.bank.ms_accounts.web.dtos.requests.MovementRequest;
import com.devsu.xp.bank.ms_accounts.web.dtos.responses.MovementResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MovementController {

  private final MovementService service;
  private final MovementWebMapper mapper;

  @PostMapping("/movements")
  public ResponseEntity<MovementResponse> register(@RequestBody @Valid MovementRequest req) {
    var saved = service.register(mapper.toDomain(req));
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
  }

  @GetMapping("/accounts/{id}/movements")
  public List<MovementResponse> listByAccount(@PathVariable("id") String accountId) {
    return service.listByAccount(accountId)
      .stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
  }
}
