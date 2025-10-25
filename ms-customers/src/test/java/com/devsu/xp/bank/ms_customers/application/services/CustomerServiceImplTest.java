package com.devsu.xp.bank.ms_customers.application.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.devsu.xp.bank.ms_customers.domain.models.Customer;
import com.devsu.xp.bank.ms_customers.domain.ports.CustomerRepositoryPort;
import com.devsu.xp.bank.shared_kernel.error.BusinessException;
import com.devsu.xp.bank.shared_kernel.error.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerServiceImplTest {

  private CustomerRepositoryPort repo;
  private CustomerService service;

  @BeforeEach
  void setUp() {
    repo = mock(CustomerRepositoryPort.class);
    service = new CustomerServiceImpl(repo);
  }

  @Test
  void create_shouldGenerateId_andEnforceUniqueIdentification() {
    Customer incoming = Customer.builder()
      .name("John")
      .gender("M")
      .age(30)
      .identification("ABC123")
      .address("Street 1")
      .phone("999")
      .password("1234")
      .status(true)
      .build();

    when(repo.findByIdentification("ABC123")).thenReturn(Optional.empty());
    when(repo.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));

    Customer created = service.create(incoming);

    assertNotNull(created.getId());
    assertEquals("ABC123", created.getIdentification());
    verify(repo).save(any(Customer.class));
  }

  @Test
  void create_shouldFail_onDuplicateIdentification() {
    Customer incoming = Customer.builder().identification("ABC123").build();
    when(repo.findByIdentification("ABC123"))
      .thenReturn(Optional.of(Customer.builder().id("c-1").identification("ABC123").build()));

    assertThrows(BusinessException.class, () -> service.create(incoming));
    verify(repo, never()).save(any());
  }

  @Test
  void getById_shouldReturn_orThrow() {
    when(repo.findById("c-1")).thenReturn(Optional.of(Customer.builder().id("c-1").build()));
    assertNotNull(service.getById("c-1"));

    when(repo.findById("c-x")).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> service.getById("c-x"));
  }

  @Test
  void list_shouldDelegate() {
    when(repo.findAll()).thenReturn(List.of());
    assertNotNull(service.list());
    verify(repo).findAll();
  }

  @Test
  void update_shouldSave_andEnforceIdentificationUniqueness_whenChanged() {
    Customer current = Customer.builder()
      .id("c-1").identification("ID-OLD").name("Old").build();

    when(repo.findById("c-1")).thenReturn(Optional.of(current));
    when(repo.findByIdentification("ID-NEW")).thenReturn(Optional.empty());
    when(repo.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));

    Customer input = Customer.builder()
      .identification("ID-NEW")
      .name("New")
      .build();

    Customer updated = service.update("c-1", input);

    assertEquals("c-1", updated.getId());
    assertEquals("ID-NEW", updated.getIdentification());
    assertEquals("New", updated.getName());
  }

  @Test
  void update_shouldFail_ifIdentificationTakenByAnother() {
    Customer current = Customer.builder().id("c-1").identification("ID-OLD").build();
    when(repo.findById("c-1")).thenReturn(Optional.of(current));
    when(repo.findByIdentification("ID-NEW"))
      .thenReturn(Optional.of(Customer.builder().id("c-2").identification("ID-NEW").build()));

    Customer input = Customer.builder().identification("ID-NEW").build();
    assertThrows(BusinessException.class, () -> service.update("c-1", input));
    verify(repo, never()).save(any());
  }

  @Test
  void delete_shouldReturnNotFound_ifMissing() {
    when(repo.deleteById("c-x")).thenReturn(false);
    assertThrows(NotFoundException.class, () -> service.delete("c-x"));
  }
}
