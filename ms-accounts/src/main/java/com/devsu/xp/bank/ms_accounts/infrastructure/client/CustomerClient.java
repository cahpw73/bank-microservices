package com.devsu.xp.bank.ms_accounts.infrastructure.client;

import com.devsu.xp.bank.shared_kernel.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CustomerClient {

  private final RestTemplate restTemplate;

  @Value("${ms.customers.base-url:http://localhost:8080}")
  private String customersBaseUrl;

  public String getCustomerName(String customerId) {
    try {
      ResponseEntity<CustomerResponse> response =
        restTemplate.getForEntity(customersBaseUrl + "/customers/" + customerId, CustomerResponse.class);

      CustomerResponse body = response.getBody();
      if (body == null || body.person == null || body.person.name == null) {
        throw new NotFoundException("CUSTOMER_NOT_FOUND", "Customer not found");
      }
      return body.person.name;
    } catch (RestClientException e) {
      throw new NotFoundException("CUSTOMER_NOT_FOUND", "Customer not found");
    }
  }

  // DTOs que reflejan el JSON de ms-customers
  public static final class CustomerResponse {
    public String id;
    public Boolean status;
    public PersonResponse person;
  }

  public static final class PersonResponse {
    public String id;
    public String name;
    public String gender;
    public Integer age;
    public String identification;
    public String address;
    public String phone;
  }
}
