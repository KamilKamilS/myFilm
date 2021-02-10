package com.myfilm.service;

import com.myfilm.model.Customer;
import com.myfilm.repository.CustomerRepository;
import com.myfilm.service.exception.CustomerDataNotValidException;
import com.myfilm.service.exception.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public Customer createNewCustomer(Customer customer) throws CustomerDataNotValidException {
        if(customerDataValid(customer)) {
            return customerRepository.saveAndFlush(customer);
        } else {
            throw new CustomerDataNotValidException();
        }
    }

    public Customer readCustomer(Long customerId) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotFoundException(String.valueOf(customerId));
        }
    }

    private boolean customerDataValid(Customer customer) {
        return validEmail(customer.getEmail()) && validPhoneNumber(customer.getPhoneNumber())
                && validAdress(customer.getAdress());
    }

    private boolean validEmail(String email) {
        return email.length() > 4 ? true : false;
    }

    private boolean validPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 9 ? true : false;
    }

    private boolean validAdress(String adress) {
        return true;
    }
}

