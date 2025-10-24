package com.devsu.xp.bank.ms_customers.domain.events;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
public abstract class CustomerEvent {
  private final String eventId = UUID.randomUUID().toString();
  private final OffsetDateTime occurredAt = OffsetDateTime.now();
  private final String customerId;

  protected CustomerEvent(String customerId) {
      this.customerId = customerId;
  }

  public abstract String eventType();
}
