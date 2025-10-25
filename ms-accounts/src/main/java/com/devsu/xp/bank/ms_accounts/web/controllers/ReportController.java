package com.devsu.xp.bank.ms_accounts.web.controllers;

import com.devsu.xp.bank.ms_accounts.application.services.ReportService;
import com.devsu.xp.bank.ms_accounts.infrastructure.mappers.ReportMapper;
import com.devsu.xp.bank.ms_accounts.web.dtos.responses.ReportResponse;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportController {

  private final ReportService reportService;
  private final ReportMapper mapper;

  @GetMapping("/reports")
  public List<ReportResponse> getReport(
    @RequestParam @NotBlank String customerId,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
  ) {
    return reportService.getReport(customerId, from, to)
      .stream()
      .map(mapper::toResponse)
      .toList();
  }
}
