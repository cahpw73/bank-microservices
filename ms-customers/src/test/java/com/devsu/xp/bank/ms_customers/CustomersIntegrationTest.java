package com.devsu.xp.bank.ms_customers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomersIntegrationTest {

  @LocalServerPort
  int port;

  @Autowired
  TestRestTemplate rest;

  @Test
  void create_get_delete_customer() {
    // 1) Crear
    Map<String, Object> person = new HashMap<>();
    person.put("name", "Jane Roe");
    person.put("gender", "F");
    person.put("age", 28);
    person.put("identification", "ID-987");
    person.put("address", "Street 2");
    person.put("phone", "555-222");

    Map<String, Object> body = new HashMap<>();
    body.put("password", "1234");
    body.put("status", true);
    body.put("person", person);

    ResponseEntity<Map> created = rest.postForEntity(url("/customers"), body, Map.class);
    assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    String id = (String) created.getBody().get("id");
    assertThat(id).isNotBlank();

    // 2) Obtener
    ResponseEntity<Map> found = rest.getForEntity(url("/customers/" + id), Map.class);
    assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
    Map personResp = (Map) found.getBody().get("person");
    assertThat(personResp.get("name")).isEqualTo("Jane Roe");

    // 3) Eliminar
    rest.delete(url("/customers/" + id));

    // 4) Verificar 404 al volver a consultar
    ResponseEntity<String> notFound = rest.getForEntity(url("/customers/" + id), String.class);
    assertThat(notFound.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  private String url(String path) {
    return "http://localhost:" + port + path;
  }
}
