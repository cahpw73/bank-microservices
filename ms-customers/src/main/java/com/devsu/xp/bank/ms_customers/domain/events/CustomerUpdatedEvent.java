package com.devsu.xp.bank.ms_customers.domain.events;

public class CustomerUpdatedEvent extends CustomerEvent {
  public CustomerUpdatedEvent(String customerId) {
    super(customerId);
  }

  @Override
  public String eventType() {
    return "CUSTOMER_UPDATED";
  }

}
