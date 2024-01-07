package com.unibuc.shop.services;

import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.*;
import com.unibuc.shop.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> findByFullName(String name) {
        return customerRepository.findByFullName(name);
    }

    public Customer create(Customer customer) {
        Optional<Customer> existingCustomerSameName = customerRepository.findByFullName(customer.getFullName());
        existingCustomerSameName.ifPresent(e -> {
            throw new DuplicateException();
        });
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer)
    {
        Optional<Customer> existingCustomer = customerRepository.findById(id.longValue());
        if(existingCustomer.isPresent()){
            Customer obj =  existingCustomer.get();
            obj.setFullName(customer.getFullName());
            obj.setMobileNumber(customer.getMobileNumber());
            obj.setEmail(customer.getEmail());
            return customerRepository.save(obj);
        }else
        {
            throw new NotFoundException(id.longValue());
        }
    }

    public void deleteById(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            customerRepository.deleteById(id);
        }else
        {
            throw new NotFoundException(id.longValue());
        }

    }


}
