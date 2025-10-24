package com.devsu.xp.bank.ms_customers.domain.events;

public class CustomerCreatedEvent extends CustomerEvent {
  public CustomerCreatedEvent(String customerId) {
    super(customerId);
  }

  @Override
  public String eventType() {
    return "CUSTOMER_CREATED";
  }
}
