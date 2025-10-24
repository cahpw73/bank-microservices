package com.devsu.xp.bank.ms_customers.domain.ports;

import com.devsu.xp.bank.ms_customers.domain.events.CustomerEvent;

public interface MessagePublisherPort {
  void publish(CustomerEvent event);
}
