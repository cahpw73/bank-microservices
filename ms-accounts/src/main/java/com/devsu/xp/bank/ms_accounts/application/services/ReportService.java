package com.devsu.xp.bank.ms_accounts.application.services;

import com.devsu.xp.bank.ms_accounts.domain.models.ReportItem;
import java.time.LocalDate;
import java.util.List;

public interface ReportService {
  List<ReportItem> getReport(String customerId, LocalDate from, LocalDate to);
}
