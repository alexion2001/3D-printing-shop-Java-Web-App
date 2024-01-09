package com.unibuc.shop.service;


import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.Customer;
import com.unibuc.shop.repository.CustomerRepository;
import com.unibuc.shop.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    Customer customer = new Customer(1L,"maria","0728282106", "maria@gmail.com");


    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void whenCustomerAlreadyExists_create_throwsDuplicateException() {
        // Arrange
        Customer customer = new Customer();
        customer.setFullName("John Doe");
        when(customerRepository.findByFullName(customer.getFullName()))
                .thenReturn(Optional.of(customer));

        // Act
        DuplicateException exception = assertThrows(DuplicateException.class,
                () -> customerService.create(customer));

        // Assert
        assertEquals("An entry with the same data already exists.", exception.getMessage());
        verify(customerRepository, times(0)).save(customer);

    }
    @Test
    void whenCustomerDoesntExist_create_savesTheCustomer() {

        // Arrange
        Customer customer = new Customer();
        customer.setFullName("maria");

        Customer savedCustomer = this.customer;
        when(customerRepository.save(customer)).thenReturn(savedCustomer);

        // Act
        Customer result = customerService.create(customer);

        // Assert
        assertNotNull(result);
        assertEquals(savedCustomer.getCustomerId(), result.getCustomerId());
        assertEquals(savedCustomer.getFullName(), result.getFullName());
        assertEquals(customer.getFullName(), result.getFullName());
        verify(customerRepository).findByFullName(customer.getFullName());
        verify(customerRepository).save(customer);
    }

    @Test
    void whenCustomerDoesntExists_findById_returnsEmptyOptional() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        // Act
        Optional<Customer> result = customerService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void whenCustomerExists_update() {
        // Arrange
        Customer oldCustomer = this.customer;
        when(customerRepository.findById(1L)).thenReturn(Optional.of(oldCustomer));

        Customer newCustomer = this.customer;
        newCustomer.setEmail("dan@yahoo.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(newCustomer);

        // Act
        Customer result = customerService.updateCustomer(1L, newCustomer);

        // Assert
        assertNotNull(result);
        assertEquals(newCustomer.getCustomerId(), result.getCustomerId());
        assertEquals(newCustomer.getFullName(), result.getFullName());
        assertEquals(newCustomer.getEmail(), result.getEmail());
        assertEquals(newCustomer.getMobileNumber(), result.getMobileNumber());
        assertEquals(newCustomer.getMobileNumber(), result.getMobileNumber());

        verify(customerRepository).findById(1L);
        verify(customerRepository).save(any(Customer.class));
    }


    @Test
    void whenCustomerExists_findById_returnsTheCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        Optional<Customer> result = customerService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(customer.getCustomerId(), result.get().getCustomerId());
    }

    @Test
    void whenCustomerExists_deleteById_deletesTheCustomer() {
        // Arrange
        Customer customer = this.customer;
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        assertDoesNotThrow(() -> customerService.deleteById(1L));

        // Assert
        verify(customerRepository).findById(1L);
        verify(customerRepository).deleteById(1L);
    }

    @Test
    void whenCustomerDoesntExist_deleteById_throwsNotFoundException() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> customerService.deleteById(1L));

        // Assert.
        assertEquals("The search with id 1 doesn't exist.", exception.getMessage());
        verify(customerRepository).findById(1L);
        verify(customerRepository, times(0)).deleteById(1L);
    }


}
