package com.devsu.xp.bank.ms_accounts.domain.ports;

import java.time.OffsetDateTime;
import java.util.List;

public interface ReportQueryPort {
  // Returning raw Object[]/map would acoplar. Definiremos un ReportItem domain model en el paso de reportes.
  // Por ahora solo declaramos la intención.
  List<?> findStatementByCustomerAndDateRange(String customerId, OffsetDateTime from, OffsetDateTime to);
}
