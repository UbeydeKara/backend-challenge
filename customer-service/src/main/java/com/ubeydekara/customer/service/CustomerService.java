package com.ubeydekara.customer.service;

import com.ubeydekara.customer.model.Customer;
import com.ubeydekara.customer.response.CustomerResponse;
import com.ubeydekara.payload.CustomerPayload;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> getAll();
    Customer create(Customer customer);
    Customer update(Customer customer);
    Boolean deleteById(UUID id);
    List<Customer> getCustomersWithoutOrder();
    List<CustomerResponse> findByNameContaining(String name);
    CustomerPayload getById(UUID customerId);
}
