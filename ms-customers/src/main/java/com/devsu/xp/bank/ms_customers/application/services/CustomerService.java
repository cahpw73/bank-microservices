package com.devsu.xp.bank.ms_customers.application.services;

import com.devsu.xp.bank.ms_customers.domain.models.Customer;
import java.util.List;

public interface CustomerService {
  Customer create(Customer customer);
  Customer getById(String id);
  List<Customer> list();
  Customer update(String id, Customer customer);
  void delete(String id);
}
