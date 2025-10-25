package com.devsu.xp.bank.ms_accounts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") 
class AccountsIntegrationTest {

  @LocalServerPort
  int port;

  @Autowired
  TestRestTemplate rest;

  @Test
  void createAccount_registerDeposit_thenListMovements() {
    String unique = String.valueOf(System.nanoTime());
    // 1) Crear cuenta
    Map<String, Object> account = new HashMap<>();
    account.put("number", "ACC-" + unique);
    account.put("type", "SAVINGS");
    account.put("openingBalance", 1000.0);
    account.put("status", true);
    account.put("customerId", "cust-1");

    ResponseEntity<Map> createdAcc = rest.postForEntity(url("/accounts"), account, Map.class);
    assertThat(createdAcc.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    String accountId = (String) createdAcc.getBody().get("id");
    assertThat(accountId).isNotBlank();

    // 2) Registrar movimiento (DEPOSIT 250)
    Map<String, Object> mov = new HashMap<>();
    mov.put("accountId", accountId);
    mov.put("type", "DEPOSIT");
    mov.put("amount", 250.0);

    ResponseEntity<Map> createdMov = rest.postForEntity(url("/movements"), mov, Map.class);
    assertThat(createdMov.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(((Number) createdMov.getBody().get("resultingBalance")).doubleValue()).isEqualTo(1250.0);

    // 3) Listar movimientos
    ResponseEntity<List> list = rest.getForEntity(url("/accounts/" + accountId + "/movements"), List.class);
    assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(list.getBody()).hasSize(1);
  }

  private String url(String path) {
    return "http://localhost:" + port + path;
    }
}
