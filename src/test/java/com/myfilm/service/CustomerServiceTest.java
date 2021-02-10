package com.myfilm.service;

import com.myfilm.model.Customer;
import com.myfilm.repository.CustomerRepository;
import com.myfilm.service.exception.CustomerDataNotValidException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    public void shouldCreateNewCustomer() throws CustomerDataNotValidException {
        // given
        Customer customer = new Customer();
        customer.setName("Kamil");
        customer.setSurname("Akmilaa");
        customer.setEmail("aaahahha");
        customer.setPhoneNumber("999999999");
        when(customerRepository.saveAndFlush(customer)).thenReturn(customer);

        // when
        Customer savedCustomer = customerService.createNewCustomer(customer);

        // then
        assertThat(savedCustomer).isEqualTo(customer);
    }


    @Test
    public void shouldThrowExceptionWhenCreateNewCustomerWithWrongData() throws CustomerDataNotValidException {
        // given
        Customer customer = new Customer();
        customer.setName("Kamil");
        customer.setSurname("Akmilaa");
        customer.setEmail("aaahahha");
        customer.setPhoneNumber("99");
        when(customerRepository.saveAndFlush(customer)).thenReturn(customer);

        // when

        // then
        assertThatExceptionOfType(CustomerDataNotValidException.class)
                .isThrownBy(() -> customerService.createNewCustomer(customer));
    }

}