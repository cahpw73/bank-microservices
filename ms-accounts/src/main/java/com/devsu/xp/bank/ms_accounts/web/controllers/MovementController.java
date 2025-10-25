package com.devsu.xp.bank.ms_accounts.web.controllers;

import com.devsu.xp.bank.ms_accounts.application.services.MovementService;
import com.devsu.xp.bank.ms_accounts.infrastructure.mappers.MovementWebMapper;
import com.devsu.xp.bank.ms_accounts.web.dtos.requests.MovementRequest;
import com.devsu.xp.bank.ms_accounts.web.dtos.responses.MovementResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class MovementController {

  private final MovementService service;
  private final MovementWebMapper mapper;

  @PostMapping("/movements")
  public ResponseEntity<MovementResponse> register(@RequestBody @Valid MovementRequest req) {
    log.info("POST /movements - Registering movement: accountId={}, type={}, amount={}",
        req.getAccountId(), req.getType(), req.getAmount());
    var saved = service.register(mapper.toDomain(req));
    log.info("Movement registered: id={}, resultingBalance={}",
        saved.getId(), saved.getResultingBalance());
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
  }

  @GetMapping("/accounts/{id}/movements")
  public List<MovementResponse> listByAccount(@PathVariable("id") String accountId) {
    log.info("GET /accounts/{}/movements - Listing movements", accountId);
    List<MovementResponse> list = service.listByAccount(accountId)
      .stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
    log.info("Movements listed for accountId {}: count={}", accountId, list.size());
    return list;
  }
}
